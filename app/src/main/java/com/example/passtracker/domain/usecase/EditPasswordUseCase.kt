package com.example.passtracker.domain.usecase

import com.example.passtracker.domain.model.UserEditPassword
import com.example.passtracker.domain.repository.ProfileRepository

class EditPasswordUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(password: UserEditPassword): Result<Unit> {
        return repository.editPassword(password)
    }
}