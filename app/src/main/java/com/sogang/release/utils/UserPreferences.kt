package com.sogang.release.utils

import android.content.Context
import android.content.SharedPreferences

object UserPreferences {
    private const val PREF_NAME = "release_prefs"
    private const val KEY_ACCESS_TOKEN = "access_token"
    private const val KEY_REFRESH_TOKEN = "refresh_token"

    private var prefs: SharedPreferences? = null

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveTokens(accessToken: String?, refreshToken: String?) {
        if (accessToken.isNullOrEmpty() || refreshToken.isNullOrEmpty()) {
            throw IllegalArgumentException("Tokens must not be null or empty")
        }

        // `prefs`가 null이면 초기화되지 않은 상태이므로 예외 발생
        val sharedPreferences = prefs ?: throw IllegalStateException("UserPreferences is not initialized")

        sharedPreferences.edit().apply {
            putString(KEY_ACCESS_TOKEN, accessToken)
            putString(KEY_REFRESH_TOKEN, refreshToken)
            apply()
        }
    }

    fun getAccessToken(): String? {
        return prefs?.getString(KEY_ACCESS_TOKEN, null)
            ?: throw IllegalStateException("UserPreferences is not initialized")
    }

    fun getRefreshToken(): String? {
        return prefs?.getString(KEY_REFRESH_TOKEN, null)
            ?: throw IllegalStateException("UserPreferences is not initialized")
    }

    fun clearTokens() {
        prefs?.edit()?.clear()?.apply()
            ?: throw IllegalStateException("UserPreferences is not initialized")
    }
}