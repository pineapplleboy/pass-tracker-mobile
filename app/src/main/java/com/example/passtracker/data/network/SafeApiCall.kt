package com.example.passtracker.data.network

import retrofit2.Response

suspend fun <T, R> safeApiCall(
    apiCall: suspend () -> Response<T>,
    transform: (T) -> R
): Result<R> {
    return try {
        val response = apiCall()
        if(response.isSuccessful) {
            response.body()?.let {
                Result.success(transform(it))
            } ?: Result.failure(Exception("Empty response body"))
        }
        else {
            Result.failure(Throwable(message = response.message()))
        }
    } catch(e: Exception) {
        Result.failure(Throwable(message = e.localizedMessage ?: "Unknown error"))
    }
}