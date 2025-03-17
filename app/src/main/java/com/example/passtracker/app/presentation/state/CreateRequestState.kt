package com.example.passtracker.app.presentation.state

sealed interface CreateRequestState {
    data object Initial: CreateRequestState
    data object Loading: CreateRequestState
    data class Failure(val message: String?): CreateRequestState
    data object Success: CreateRequestState
}