package com.example.passtracker.app.presentation.state

sealed interface LoginState {
    data object Initial : LoginState
    data object Loading : LoginState
    data class Failure(val message: String?) : LoginState
    object Success : LoginState
}