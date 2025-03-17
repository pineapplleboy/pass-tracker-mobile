package com.example.passtracker.domain.usecase

import com.example.passtracker.domain.model.RequestCreate
import com.example.passtracker.domain.repository.RequestRepository

class CreateRequestUseCase(
    private val repository: RequestRepository
) {
    suspend operator fun invoke(request: RequestCreate): Result<Unit> {
        return repository.createRequest(request)
    }
}