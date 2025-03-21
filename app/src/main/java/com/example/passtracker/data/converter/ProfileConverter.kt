package com.example.passtracker.data.converter

import com.example.passtracker.data.model.ProfileDTO
import com.example.passtracker.domain.model.Profile

fun ProfileDTO.toDomainModel(): Profile {
    return Profile(
        name = this.name,
        email = this.email,
        group = this.group
    )
}