package com.example.passtracker.domain.usecase

import com.example.passtracker.domain.repository.ProfileRepository
import com.example.passtracker.domain.model.AuthResult
import com.example.passtracker.domain.model.UserRegister

class RegisterUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(user: UserRegister): AuthResult {
        return repository.register(user)
//        return AuthResult.Success
    }
}