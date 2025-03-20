package com.example.passtracker.data.network

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun saveTokens(accessToken: String, refreshToken: String, accessTokenExpireTime: String) {
        prefs.edit()
            .putString("accessToken", accessToken)
            .putString("refreshToken", refreshToken)
            .putString("accessTokenExpireTime", accessTokenExpireTime)
            .apply()
    }

    fun getAccessToken(): String? = prefs.getString("accessToken", null)
    fun getRefreshToken(): String? = prefs.getString("refreshToken", null)
    fun getAccessTokenExpireTime(): String? = prefs.getString("accessTokenExpireTime", null)

    fun clearTokens() {
        prefs.edit()
            .remove("accessToken")
            .remove("refreshToken")
            .remove("accessTokenExpireTime")
            .apply()
    }
}