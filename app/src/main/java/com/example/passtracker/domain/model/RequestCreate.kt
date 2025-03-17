package com.example.passtracker.domain.model

data class RequestCreate(
    val startDate: String,
    val finishDate: String,
    val typeRequest: TypeRequest,
    val photo: String?
)