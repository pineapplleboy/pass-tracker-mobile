package com.example.passtracker.data.converter

import com.example.passtracker.data.model.PageInfoDTO
import com.example.passtracker.domain.model.PageInfo

fun PageInfoDTO.toDomainModel(): PageInfo {
    return PageInfo(
        size = this.size,
        count = this.count,
        current = this.current
    )
}