package com.example.passtracker.data.converter

import com.example.passtracker.data.model.RequestCreateDTO
import com.example.passtracker.domain.model.RequestCreate

fun RequestCreate.toDTO(): RequestCreateDTO {
    return RequestCreateDTO(
        startDate = this.startDate,
        finishDate = this.finishDate,
        typeRequest = this.typeRequest.toDTO(),
        photo = this.photo
    )
}