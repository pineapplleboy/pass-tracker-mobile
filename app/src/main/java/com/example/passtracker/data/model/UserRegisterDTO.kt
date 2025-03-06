package com.example.passtracker.data.model

data class UserRegisterDTO(
    val secondName: String,
    val firstName: String,
    val middleName: String,
    val group: Int,
    val email: String,
    val password: String
)