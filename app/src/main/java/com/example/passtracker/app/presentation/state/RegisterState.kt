package com.example.passtracker.app.presentation.state

sealed interface RegisterState {
    data object Initial : RegisterState
    data object Loading : RegisterState
    data class Failure(val message: String?) : RegisterState
}