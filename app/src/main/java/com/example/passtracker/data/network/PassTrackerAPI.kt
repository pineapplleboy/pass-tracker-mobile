package com.example.passtracker.data.network

import com.example.passtracker.data.model.TokenDTO
import com.example.passtracker.data.model.UserLoginDTO
import com.example.passtracker.data.model.UserRegisterDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PassTrackerAPI {

    @POST("user/register")
    suspend fun register(@Body user: UserRegisterDTO): Response<TokenDTO>

    @POST("user/login")
    suspend fun login(@Body user: UserLoginDTO): Response<TokenDTO>

    @POST("user/logout")
    suspend fun logout(): Response<Unit>
}