package com.example.passtracker.domain.usecase

import com.example.passtracker.domain.repository.ProfileRepository

class LogoutUseCase(
    val repository: ProfileRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.logout()
    }
}