package com.example.passtracker.data.model

data class TokenDTO(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpireTime: String
)