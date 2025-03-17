package com.example.passtracker.data.converter

import com.example.passtracker.data.model.RequestChangeDTO
import com.example.passtracker.domain.model.RequestChange

fun RequestChange.toDTO(): RequestChangeDTO {
    return RequestChangeDTO(
        startDate = this.startDate,
        finishDate = this.finishDate,
        typeRequest = this.typeRequest.toDTO(),
        photo = this.photo
    )
}