package com.example.passtracker.data.network

import android.content.Context
import android.content.Intent
import com.example.passtracker.app.ui.activity.MainActivity
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val sessionManager: SessionManager,
    private val appContext: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = sessionManager.getAccessToken()
        val requestWithToken = if (!token.isNullOrEmpty()) {
            originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            originalRequest
        }

        val response = chain.proceed(requestWithToken)

        if (response.code == 401) {
            sessionManager.clearTokens()

            val intent = Intent(appContext, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
            appContext.startActivity(intent)
        }

        return response
    }
}
