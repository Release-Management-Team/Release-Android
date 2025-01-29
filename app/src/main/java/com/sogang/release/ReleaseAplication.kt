package com.sogang.release

import android.app.Application
import com.sogang.release.utils.UserPreferences

class ReleaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        UserPreferences.init(this)
    }
}