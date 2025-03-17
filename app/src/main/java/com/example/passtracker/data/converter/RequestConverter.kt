package com.example.passtracker.data.converter

import com.example.passtracker.data.model.RequestDTO
import com.example.passtracker.domain.model.Request

fun RequestDTO.toDomainModel(): Request {
    return Request(
        id = this.id,
        userName = this.userName,
        startDate = this.startDate,
        finishDate = this.finishDate,
        typeRequest = this.typeRequest.toDomainModel(),
        statusRequest = this.statusRequest.toDomainModel(),
        group = this.group,
        comment = this.comment,
        photo = this.photo
    )
}