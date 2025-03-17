package com.example.passtracker.domain.usecase

import com.example.passtracker.domain.repository.RequestRepository

class DeleteRequestUseCase(
    private val repository: RequestRepository
) {
    suspend operator fun invoke(id: String): Result<Unit> {
        return repository.deleteRequest(id)
    }
}