package com.example.passtracker.data.converter

import com.example.passtracker.data.model.RequestShortDTO
import com.example.passtracker.domain.model.RequestShort

fun RequestShortDTO.toDomainModel(): RequestShort {
    return RequestShort(
        id = this.id,
        userName = this.userName,
        startDate = this.startDate,
        finishDate = this.finishDate,
        typeRequest = this.typeRequest.toDomainModel(),
        statusRequest = this.statusRequest.toDomainModel(),
        group = this.group
    )
}