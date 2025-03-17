package com.example.passtracker.domain.model

import java.util.UUID

data class Request(
    val id: UUID,
    val userName: String?,
    val startDate: String,
    val finishDate: String,
    val typeRequest: TypeRequest,
    val statusRequest: StatusRequest,
    val group: Int?,
    val comment: String?,
    val photo: String?
)