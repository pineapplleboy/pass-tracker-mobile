package com.example.passtracker.data.converter

import com.example.passtracker.data.model.RequestPagedListDTO
import com.example.passtracker.domain.model.RequestPagedList

fun RequestPagedListDTO.toDomainModel(): RequestPagedList {
    return RequestPagedList(
        requests = this.requests?.map{ it.toDomainModel() },
        pagination = this.pagination.toDomainModel()
    )
}