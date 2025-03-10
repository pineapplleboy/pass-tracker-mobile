package com.example.passtracker.data.repository

import com.example.passtracker.data.converter.toDTO
import com.example.passtracker.data.converter.toDomainModel
import com.example.passtracker.data.network.PassTrackerAPI
import com.example.passtracker.data.network.SessionManager
import com.example.passtracker.domain.model.AuthResult
import com.example.passtracker.domain.model.Profile
import com.example.passtracker.domain.model.UserLogin
import com.example.passtracker.domain.model.UserRegister
import com.example.passtracker.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val api: PassTrackerAPI,
    private val sessionManager: SessionManager
): ProfileRepository {

    override suspend fun register(user: UserRegister): AuthResult {
        return try {
            val response = api.register(user.toDTO())
            if(response.isSuccessful) {
                response.body()?.let {
                    sessionManager.saveAuthToken(it.token)
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
            if(response.isSuccessful) {
                response.body()?.let {
                    sessionManager.saveAuthToken(it.token)
                    AuthResult.Success
                } ?: AuthResult.Error("Empty response")
            } else {
                AuthResult.Error("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            AuthResult.Error(e.localizedMessage ?: "Unknown error")
        }
    }

    override suspend fun logout(): AuthResult {
        return try {
            val response = api.logout()
            if(response.isSuccessful) {
                AuthResult.Success
            } else {
                AuthResult.Error("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            AuthResult.Error(e.localizedMessage ?: "Unknown error")
        }
    }

    override suspend fun getProfile(): Result<Profile> {
        return try {
            val response = api.getProfile()
            if(response.isSuccessful) {
                response.body()?.let {
                    Result.success(it.toDomainModel())
                } ?: Result.failure(Throwable(message = "Empty response"))
            }
            else {
                Result.failure(Throwable(message = response.message()))
            }
        } catch(e: Exception) {
            Result.failure(Throwable(message = e.localizedMessage ?: "Unknown error"))
        }
    }
}