package com.example.passtracker.domain.usecase

import com.example.passtracker.data.network.SessionManager

class CheckAuthorizationUseCase(
    private val sessionManager: SessionManager
) {

    operator fun invoke(): Boolean {
        val token = sessionManager.getAccessToken()
        return !token.isNullOrEmpty()
    }
}