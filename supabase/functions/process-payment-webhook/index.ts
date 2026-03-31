import { serve } from "https://deno.land/std@0.168.0/http/server.ts"
import { createClient } from "https://esm.sh/@supabase/supabase-js@2"
import { crypto } from "https://deno.land/std@0.168.0/crypto/mod.ts"
import { toHashString } from "https://deno.land/std@0.168.0/crypto/to_hash_string.ts"

serve(async (req) => {
  const formData = await req.formData()
  const payload = Object.fromEntries(formData.entries())
  
  const merchantId = payload.merchant_id
  const orderId = payload.order_id
  const payhereAmount = payload.payhere_amount
  const payhereCurrency = payload.payhere_currency
  const statusCode = payload.status_code
  const md5sig = payload.md5sig
  
  const merchantSecret = Deno.env.get('PAYHERE_SECRET') ?? ''
  
  // Verify PayHere Signature
  // MD5(merchant_id + order_id + payhere_amount + payhere_currency + status_code + STR_UPPER(MD5(merchant_secret)))
  const secretHash = toHashString(await crypto.subtle.digest("MD5", new TextEncoder().encode(merchantSecret))).toUpperCase()
  const checkStr = `${merchantId}${orderId}${payhereAmount}${payhereCurrency}${statusCode}${secretHash}`
  const localSig = toHashString(await crypto.subtle.digest("MD5", new TextEncoder().encode(checkStr))).toUpperCase()

  if (localSig !== md5sig) {
    console.error("Invalid signature")
    return new Response("Invalid Signature", { status: 400 })
  }

  const supabase = createClient(
    Deno.env.get('SUPABASE_URL') ?? '',
    Deno.env.get('SUPABASE_SERVICE_ROLE_KEY') ?? ''
  )

  if (statusCode === "2") { // 2 = Success
    // Update order status
    await supabase.from('orders').update({ status: 'completed', payment_id: payload.payment_id }).eq('id', orderId)
    
    // Update total spent for user
    const { data: order } = await supabase.from('orders').select('user_id, total_amount').eq('id', orderId).single()
    if (order) {
      await supabase.rpc('increment_user_spend', { uid: order.user_id, amount: order.total_amount })
    }
  } else {
    await supabase.from('orders').update({ status: 'failed' }).eq('id', orderId)
  }

  return new Response("OK", { status: 200 })
})
