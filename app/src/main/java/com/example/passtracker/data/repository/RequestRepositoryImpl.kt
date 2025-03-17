package com.example.passtracker.data.repository

import com.example.passtracker.data.converter.toDTO
import com.example.passtracker.data.converter.toDomainModel
import com.example.passtracker.data.network.PassTrackerAPI
import com.example.passtracker.data.network.safeApiCall
import com.example.passtracker.domain.model.Request
import com.example.passtracker.domain.model.RequestChange
import com.example.passtracker.domain.model.RequestCreate
import com.example.passtracker.domain.model.RequestPagedList
import com.example.passtracker.domain.repository.RequestRepository

class RequestRepositoryImpl(
    private val api: PassTrackerAPI
): RequestRepository {
    override suspend fun createRequest(request: RequestCreate): Result<Unit> {
        return safeApiCall(
            apiCall = {
                api.createRequest(request.toDTO())
            },
            transform = { }
        )
    }

    override suspend fun changeRequest(id: String, request: RequestChange): Result<Unit> {
        return safeApiCall(
            apiCall = {
                api.changeRequest(
                    id = id,
                    request = request.toDTO()
                )
            },
            transform = { }
        )
    }

    override suspend fun deleteRequest(id: String): Result<Unit> {
        return safeApiCall(
            apiCall = {
                api.deleteRequest(id)
            },
            transform = { }
        )
    }

    override suspend fun getRequestInfo(id: String): Result<Request> {
        return safeApiCall(
            apiCall = {
                api.getRequestInfo(id)
            },
            transform = { it.toDomainModel() }
        )
    }

    override suspend fun getAllUserRequest(page: Int, size: Int): Result<RequestPagedList> {
        return safeApiCall(
            apiCall = {
                api.getAllUserRequests(page = page, size = size)
            },
            transform = { it.toDomainModel() }
        )
    }
}