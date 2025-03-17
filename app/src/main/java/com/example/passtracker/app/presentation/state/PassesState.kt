package com.example.passtracker.app.presentation.state

import com.example.passtracker.domain.model.RequestShort

sealed interface PassesState {
    data object Initial : PassesState
    data object Loading : PassesState
    data class Failure(val message: String?) : PassesState
    data class Success(val requests: List<RequestShort>) : PassesState
}