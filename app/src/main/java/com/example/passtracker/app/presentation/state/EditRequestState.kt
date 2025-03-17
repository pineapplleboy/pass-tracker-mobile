package com.example.passtracker.app.presentation.state

import com.example.passtracker.domain.model.Request

sealed interface EditRequestState {
    data object Loading: EditRequestState
    data object Initial: EditRequestState
    data class Failure(val message: String?) : EditRequestState
    data class Success(val request: Request) : EditRequestState
    data object Deleted: EditRequestState
}