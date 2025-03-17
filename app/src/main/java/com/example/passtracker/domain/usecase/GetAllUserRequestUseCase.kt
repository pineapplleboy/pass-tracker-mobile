package com.example.passtracker.domain.usecase

import com.example.passtracker.domain.model.RequestPagedList
import com.example.passtracker.domain.repository.RequestRepository

class GetAllUserRequestUseCase(
    private val repository: RequestRepository
) {
    suspend operator fun invoke(size: Int, page: Int): Result<RequestPagedList> {
        return repository.getAllUserRequest(size = size, page = page)
    }
}