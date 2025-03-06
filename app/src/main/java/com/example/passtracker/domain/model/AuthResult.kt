package com.example.passtracker.domain.model

sealed class AuthResult {
    data object Success: AuthResult()
    data class Error(val message: String): AuthResult()
}