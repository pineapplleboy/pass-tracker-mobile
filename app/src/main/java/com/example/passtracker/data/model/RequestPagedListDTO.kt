package com.example.passtracker.data.model

data class RequestPagedListDTO(
    val requests: List<RequestShortDTO>?,
    val pagination: PageInfoDTO
)