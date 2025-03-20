package com.example.passtracker.data.network

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.passtracker.app.ui.activity.MainActivity
import com.example.passtracker.data.constants.DataConstants
import com.example.passtracker.data.model.RefreshTokenDTO
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthInterceptor(
    private val context: Context,
    private val sessionManager: SessionManager
) : Interceptor {

    private val api: PassTrackerAPI by lazy {
        Retrofit.Builder()
            .baseUrl(DataConstants.BASE_URL)
            .client(UnsafeOkHttpClient.getUnsafeOkHttpClient(AuthInterceptor(context, sessionManager)))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PassTrackerAPI::class.java)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (originalRequest.url.encodedPath in listOf("/user/login-refresh", "/user/login", "/user/register")) {
            return chain.proceed(originalRequest)
        }

        val accessToken = sessionManager.getAccessToken()
        val refreshToken = sessionManager.getRefreshToken()
        val accessTokenExpireTime = sessionManager.getAccessTokenExpireTime()

        val currentTime = System.currentTimeMillis()
        val localExpireTime = accessTokenExpireTime?.let {
            java.time.Instant.parse(it).toEpochMilli()
        }

        val token = if (accessToken != null && localExpireTime != null && currentTime < localExpireTime) {
            accessToken
        } else if (refreshToken != null) {
            runBlocking { refreshAccessToken() }
        } else {
            null
        }

        if (token == null) {
            sessionManager.clearTokens()
            redirectToLogin()
            return unauthorizedResponse(originalRequest)
        }

        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        val response = chain.proceed(newRequest)

        if (response.code == 401) {
            sessionManager.clearTokens()
            redirectToLogin()
        }

        return response
    }

    private fun unauthorizedResponse(request: Request): Response {
        return Response.Builder()
            .request(request)
            .protocol(okhttp3.Protocol.HTTP_1_1)
            .code(401)
            .message("Unauthorized")
            .body(okhttp3.ResponseBody.create(null, ""))
            .build()
    }

    private fun refreshAccessToken(): String? {
        val refreshToken = sessionManager.getRefreshToken() ?: return null
        return runBlocking {
            val response = api.refreshToken(RefreshTokenDTO(refreshToken))
            if (response.isSuccessful) {
                response.body()?.let { tokenDTO ->
                    sessionManager.saveTokens(
                        tokenDTO.accessToken,
                        tokenDTO.refreshToken,
                        tokenDTO.accessTokenExpireTime
                    )
                    return@runBlocking tokenDTO.accessToken
                }
            }

            null
        }
    }

    private fun redirectToLogin() {
        val intent = Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)
    }
}
