package com.alilmia.app

import android.app.Application
import com.bumptech.glide.Glide

/**
 * Application class for Al-Ilmia app
 * Initializes global configurations and dependencies
 */
class AlIlmiaApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        // Initialize Glide with custom configuration
        try {
            Glide.get(this)
        } catch (e: Exception) {
            // Handle Glide initialization error
        }
    }
    
    override fun onLowMemory() {
        super.onLowMemory()
        // Clear Glide cache on low memory
        Glide.get(this).clearMemory()
    }
    
    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        // Trim Glide cache based on memory level
        Glide.get(this).trimMemory(level)
    }
}