package com.example.passtracker.data.repository

import com.example.passtracker.data.converter.toDTO
import com.example.passtracker.data.converter.toDomainModel
import com.example.passtracker.data.model.RefreshTokenDTO
import com.example.passtracker.data.network.PassTrackerAPI
import com.example.passtracker.data.network.SessionManager
import com.example.passtracker.data.network.safeApiCall
import com.example.passtracker.domain.model.AuthResult
import com.example.passtracker.domain.model.Profile
import com.example.passtracker.domain.model.UserEditEmail
import com.example.passtracker.domain.model.UserEditPassword
import com.example.passtracker.domain.model.UserLogin
import com.example.passtracker.domain.model.UserRegister
import com.example.passtracker.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val api: PassTrackerAPI,
    private val sessionManager: SessionManager
) : ProfileRepository {

    override suspend fun register(user: UserRegister): AuthResult {
        return try {
            val response = api.register(user.toDTO())
            if (response.isSuccessful) {
                response.body()?.let {
                    sessionManager.saveTokens(it.accessToken, it.refreshToken)
                    AuthResult.Success
                } ?: AuthResult.Error("Empty response")
            } else {
                AuthResult.Error("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            AuthResult.Error(e.localizedMessage ?: "Unknown error")
        }
    }

    override suspend fun login(user: UserLogin): AuthResult {
        return try {
            val response = api.login(user.toDTO())
            if (response.isSuccessful) {
                response.body()?.let {
                    sessionManager.saveTokens(it.accessToken, it.refreshToken)
                    AuthResult.Success
                } ?: AuthResult.Error("Empty response")
            } else {
                AuthResult.Error("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            AuthResult.Error(e.localizedMessage ?: "Unknown error")
        }
    }

    override suspend fun logout(): Result<Unit> {
        return safeApiCall(
            apiCall = {
                api.logout()
            },
            transform = { }
        )
    }

    override suspend fun getProfile(): Result<Profile> {
        return safeApiCall(
            apiCall = {
                api.getProfile()
            },
            transform = {
                it.toDomainModel()
            }
        )
    }

    override suspend fun editEmail(email: UserEditEmail): Result<Unit> {
        return safeApiCall(
            apiCall = {
                api.editEmail(email.toDTO())
            },
            transform = {}
        )
    }

    override suspend fun editPassword(password: UserEditPassword): Result<Unit> {
        return safeApiCall(
            apiCall = {
                api.editPassword(password.toDTO())
            },
            transform = {}
        )
    }

    override suspend fun refreshToken(): String? {
        return try {
            val refreshToken = sessionManager.getAccessToken() ?: return null
            val response = api.refreshToken(RefreshTokenDTO(refreshToken))

            if (response.isSuccessful) {
                response.body()?.let {
                    sessionManager.saveTokens(it.accessToken, it.refreshToken)
                    return it.accessToken
                }
            }
            null
        } catch (e: Exception) {
            null
        }
    }
}