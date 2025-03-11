package com.example.passtracker.domain.usecase

import com.example.passtracker.domain.model.UserEditEmail
import com.example.passtracker.domain.repository.ProfileRepository

class EditEmailUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(email: UserEditEmail): Result<Unit> {
        return repository.editEmail(email)
    }
}