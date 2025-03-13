package com.example.passtracker.app.presentation.state

import com.example.passtracker.domain.model.Profile

sealed interface ProfileState {
    data object Initial : ProfileState
    data object Loading : ProfileState
    data class Failure(val message: String?) : ProfileState
    data class Success(val profile: Profile) : ProfileState
}