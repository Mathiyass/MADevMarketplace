import { serve } from "https://deno.land/std@0.168.0/http/server.ts"

serve(async (req) => {
  const { title, body, tokens } = await req.json()
  const FIREBASE_SERVER_KEY = Deno.env.get('FIREBASE_SERVER_KEY')

  if (!FIREBASE_SERVER_KEY) {
    return new Response(JSON.stringify({ error: 'Config missing' }), { status: 500 })
  }

  const results = await Promise.all(tokens.map(token => 
    fetch('https://fcm.googleapis.com/fcm/send', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `key=${FIREBASE_SERVER_KEY}`
      },
      body: JSON.stringify({
        to: token,
        notification: { title, body, sound: 'default' },
        data: { click_action: 'FLUTTER_NOTIFICATION_CLICK' }
      })
    })
  ))

  const okCount = results.filter(r => r.ok).length

  return new Response(JSON.stringify({ success: true, okCount }), { 
    headers: { 'Content-Type': 'application/json' } 
  })
})
