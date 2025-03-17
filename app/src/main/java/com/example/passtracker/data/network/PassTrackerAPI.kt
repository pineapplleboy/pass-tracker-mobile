package com.example.passtracker.data.network

import com.example.passtracker.data.model.ProfileDTO
import com.example.passtracker.data.model.RefreshTokenDTO
import com.example.passtracker.data.model.RequestChangeDTO
import com.example.passtracker.data.model.RequestCreateDTO
import com.example.passtracker.data.model.RequestDTO
import com.example.passtracker.data.model.RequestPagedListDTO
import com.example.passtracker.data.model.TokenDTO
import com.example.passtracker.data.model.UserEditEmailDTO
import com.example.passtracker.data.model.UserEditPasswordDTO
import com.example.passtracker.data.model.UserLoginDTO
import com.example.passtracker.data.model.UserRegisterDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PassTrackerAPI {

    @GET("user/login-refresh")
    suspend fun refreshToken(@Body refreshTokenDTO: RefreshTokenDTO): Response<TokenDTO>

    @POST("user/register")
    suspend fun register(@Body user: UserRegisterDTO): Response<TokenDTO>

    @POST("user/login")
    suspend fun login(@Body user: UserLoginDTO): Response<TokenDTO>

    @POST("user/logout")
    suspend fun logout(): Response<Unit>

    @GET("user/profile")
    suspend fun getProfile(): Response<ProfileDTO>

    @PATCH("user/email")
    suspend fun editEmail(@Body email: UserEditEmailDTO): Response<Unit>

    @PATCH("user/password")
    suspend fun editPassword(@Body password: UserEditPasswordDTO): Response<Unit>


    @POST("request/create-request")
    suspend fun createRequest(@Body request: RequestCreateDTO): Response<Unit>

    @PUT("request/change-request/{id}")
    suspend fun changeRequest(
        @Body request: RequestChangeDTO,
        @Path("id") id: String
    ): Response<Unit>

    @DELETE("request/delete-request/{id}")
    suspend fun deleteRequest(@Path("id") id: String): Response<Unit>

    @GET("request/get-requestInfo/{id}")
    suspend fun getRequestInfo(@Path("id") id: String): Response<RequestDTO>

    @GET("request/get-all-user-requests")
    suspend fun getAllUserRequests(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<RequestPagedListDTO>

}