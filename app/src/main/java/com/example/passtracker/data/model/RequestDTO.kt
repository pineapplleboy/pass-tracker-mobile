package com.example.passtracker.data.model

import java.util.UUID

data class RequestDTO(
    val id: UUID,
    val userName: String?,
    val startDate: String,
    val finishDate: String,
    val typeRequest: TypeRequestDB,
    val statusRequest: StatusRequestDB,
    val group: Int?,
    val comment: String?,
    val photo: String?
)