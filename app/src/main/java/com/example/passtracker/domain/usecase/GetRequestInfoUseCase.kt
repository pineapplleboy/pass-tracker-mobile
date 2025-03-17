package com.example.passtracker.domain.usecase

import com.example.passtracker.domain.model.Request
import com.example.passtracker.domain.repository.RequestRepository

class GetRequestInfoUseCase(
    private val repository: RequestRepository
) {
    suspend operator fun invoke(id: String): Result<Request> {
        return repository.getRequestInfo(id)
    }
}