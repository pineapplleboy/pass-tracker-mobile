package com.example.passtracker.domain.model

data class RequestChange(
    val startDate: String,
    val finishDate: String,
    val typeRequest: TypeRequest,
    val photo: String?
)