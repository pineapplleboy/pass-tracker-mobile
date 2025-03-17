package com.example.passtracker.domain.usecase

import com.example.passtracker.domain.model.RequestChange
import com.example.passtracker.domain.repository.RequestRepository

class ChangeRequestUseCase(
    private val repository: RequestRepository
) {
    suspend operator fun invoke(id: String, request: RequestChange): Result<Unit> {
        return repository.changeRequest(id, request)
    }
}