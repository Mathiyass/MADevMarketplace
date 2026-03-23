package com.madev.marketplace.util

object Constants {
    const val SUPABASE_URL = "https://muchpzethrdrmhzztnsw.supabase.co"
    const val SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im11Y2hwemV0aHJkcm1oenp0bnN3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzQyNjIzNDAsImV4cCI6MjA4OTgzODM0MH0.rD9qMhJ8LnkOSmBbnjGFHnNVpk_6r99dx-2FxQZ8ZB8"
    const val ONESIGNAL_APP_ID = "YOUR_ONESIGNAL_APP_ID"
    const val GOOGLE_MAPS_KEY = "YOUR_MAPS_KEY"
    const val PAYHERE_MERCHANT_ID = "YOUR_MERCHANT_ID"

    const val EDGE_FN_BASE = "$SUPABASE_URL/functions/v1"
    const val EDGE_FN_SIGNED_URL = "$EDGE_FN_BASE/generate-signed-url"
    const val EDGE_FN_PUSH = "$EDGE_FN_BASE/send-push-notification"

    const val DOWNLOAD_CHANNEL_ID = "downloads"
    const val DOWNLOAD_NOTIFICATION_ID = 1001

    const val PREF_ONBOARDING_COMPLETE = "onboarding_complete"
    const val PREF_SESSION_TOKEN = "session_token"

    const val DEFAULT_LAT = 6.9271
    const val DEFAULT_LNG = 79.8612

    const val SHAKE_THRESHOLD = 12.0f
    const val SHAKE_DEBOUNCE_MS = 800L
}
