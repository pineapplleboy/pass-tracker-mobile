package com.example.passtracker.domain.repository

import com.example.passtracker.domain.model.Request
import com.example.passtracker.domain.model.RequestChange
import com.example.passtracker.domain.model.RequestCreate
import com.example.passtracker.domain.model.RequestPagedList

interface RequestRepository {

    suspend fun createRequest(request: RequestCreate): Result<Unit>

    suspend fun changeRequest(id: String, request: RequestChange): Result<Unit>

    suspend fun deleteRequest(id: String): Result<Unit>

    suspend fun getRequestInfo(id: String): Result<Request>

    suspend fun getAllUserRequest(page: Int, size: Int): Result<RequestPagedList>
}