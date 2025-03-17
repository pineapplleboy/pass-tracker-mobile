package com.example.passtracker.domain.model

data class RequestPagedList(
    val requests: List<RequestShort>?,
    val pagination: PageInfo
)