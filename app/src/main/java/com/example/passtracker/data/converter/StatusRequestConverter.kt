package com.example.passtracker.data.converter

import com.example.passtracker.data.model.StatusRequestDB
import com.example.passtracker.domain.model.StatusRequest

fun StatusRequestDB.toDomainModel(): StatusRequest {
    return StatusRequest.valueOf(this.name)
}