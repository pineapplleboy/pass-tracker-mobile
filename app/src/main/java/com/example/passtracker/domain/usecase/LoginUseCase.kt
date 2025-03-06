package com.example.passtracker.domain.usecase

import com.example.passtracker.data.repository.ProfileRepository
import com.example.passtracker.domain.model.AuthResult
import com.example.passtracker.domain.model.UserLogin

class LoginUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(user: UserLogin): AuthResult {
//        return repository.login(user)
        return AuthResult.Success
    }
}