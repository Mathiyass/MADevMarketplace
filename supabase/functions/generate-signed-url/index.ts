import { serve } from "https://deno.land/std@0.168.0/http/server.ts"
import { createClient } from "https://esm.sh/@supabase/supabase-js@2"

serve(async (req) => {
  const { productId } = await req.json()
  
  // Initialize Supabase Client
  const supabase = createClient(
    Deno.env.get('SUPABASE_URL') ?? '',
    Deno.env.get('SUPABASE_SERVICE_ROLE_KEY') ?? ''
  )

  // Get Auth user from header
  const authHeader = req.headers.get('Authorization')!
  const { data: { user }, error: authError } = await supabase.auth.getUser(authHeader.replace('Bearer ', ''))

  if (authError || !user) {
    return new Response(JSON.stringify({ error: 'Unauthorized' }), { status: 401 })
  }

  // Verify ownership (Check if user has a completed order for this product)
  const { data: ownership, error: ownerError } = await supabase
    .from('order_items')
    .select('id, orders!inner(status, user_id)')
    .eq('product_id', productId)
    .eq('orders.user_id', user.id)
    .eq('orders.status', 'completed')
    .single()

  if (ownerError || !ownership) {
    return new Response(JSON.stringify({ error: 'No purchase found' }), { status: 403 })
  }

  // Get product file path
  const { data: product } = await supabase
    .from('products')
    .select('file_path')
    .eq('id', productId)
    .single()

  if (!product?.file_path) {
    return new Response(JSON.stringify({ error: 'File not found' }), { status: 404 })
  }

  // Generate signed URL (valid for 60 mins)
  const { data: signedUrl, error: signError } = await supabase
    .storage
    .from('assets')
    .createSignedUrl(product.file_path, 3600)

  if (signError) {
    return new Response(JSON.stringify({ error: signError.message }), { status: 500 })
  }

  return new Response(JSON.stringify({ url: signedUrl.signedUrl }), {
    headers: { 'Content-Type': 'application/json' }
  })
})
