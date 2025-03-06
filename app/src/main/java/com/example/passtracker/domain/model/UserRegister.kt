package com.example.passtracker.domain.model

data class UserRegister(
    val secondName: String,
    val firstName: String,
    val middleName: String,
    val group: Int,
    val email: String,
    val password: String
)