package com.example.passtracker.domain.usecase

import com.example.passtracker.domain.model.Profile
import com.example.passtracker.domain.repository.ProfileRepository

class GetProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): Result<Profile> {
        return repository.getProfile()
    }
}