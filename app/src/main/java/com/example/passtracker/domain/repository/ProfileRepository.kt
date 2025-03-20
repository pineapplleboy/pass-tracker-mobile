package com.example.passtracker.domain.repository

import com.example.passtracker.domain.model.AuthResult
import com.example.passtracker.domain.model.Profile
import com.example.passtracker.domain.model.UserEditEmail
import com.example.passtracker.domain.model.UserEditPassword
import com.example.passtracker.domain.model.UserLogin
import com.example.passtracker.domain.model.UserRegister

interface ProfileRepository {

    suspend fun register(user: UserRegister): AuthResult

    suspend fun login(user: UserLogin): AuthResult

    suspend fun logout(): Result<Unit>

    suspend fun getProfile(): Result<Profile>

    suspend fun editEmail(email: UserEditEmail): Result<Unit>

    suspend fun editPassword(password: UserEditPassword): Result<Unit>

    suspend fun refreshToken(): String?
}