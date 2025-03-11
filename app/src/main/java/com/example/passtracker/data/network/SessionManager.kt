package com.example.passtracker.data.network

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun saveTokens(accessToken: String, refreshToken: String) {
        prefs.edit()
            .putString("accessToken", accessToken)
            .putString("refreshToken", refreshToken)
            .apply()
    }

    fun getAccessToken(): String? = prefs.getString("accessToken", null)

    fun getRefreshToken(): String? = prefs.getString("refreshToken", null)

    fun clearTokens() {
        prefs.edit()
            .remove("accessToken")
            .remove("refreshToken")
            .apply()
    }
}
