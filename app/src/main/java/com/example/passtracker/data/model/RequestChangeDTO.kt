package com.example.passtracker.data.model

data class RequestChangeDTO(
    val startDate: String,
    val finishDate: String,
    val typeRequest: TypeRequestDB,
    val photo: String?
)