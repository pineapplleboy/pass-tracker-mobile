package com.example.passtracker.domain.model

import java.util.UUID

data class RequestShort(
    val id: UUID,
    val userName: String?,
    val startDate: String,
    val finishDate: String,
    val typeRequest: TypeRequest,
    val statusRequest: StatusRequest,
    val group: Int?
)