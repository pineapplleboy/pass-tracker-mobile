package com.example.passtracker.domain.repository

import com.example.passtracker.domain.model.AuthResult
import com.example.passtracker.domain.model.Profile
import com.example.passtracker.domain.model.UserLogin
import com.example.passtracker.domain.model.UserRegister

interface ProfileRepository {

    suspend fun register(user: UserRegister): AuthResult

    suspend fun login(user: UserLogin): AuthResult

    suspend fun logout(): AuthResult

    suspend fun getProfile(): Result<Profile>
}