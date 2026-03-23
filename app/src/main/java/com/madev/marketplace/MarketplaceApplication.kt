package com.madev.marketplace

import android.app.Application

class MarketplaceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Supabase, OneSignal, etc.
    }
}
