package com.example.passtracker.data.converter

import com.example.passtracker.data.model.TypeRequestDB
import com.example.passtracker.domain.model.TypeRequest

fun TypeRequest.toDTO(): TypeRequestDB {
    return TypeRequestDB.valueOf(this.name)
}

fun TypeRequestDB.toDomainModel(): TypeRequest {
    return TypeRequest.valueOf(this.name)
}