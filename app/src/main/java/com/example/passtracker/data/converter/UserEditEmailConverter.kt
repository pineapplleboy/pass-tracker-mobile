package com.example.passtracker.data.converter

import com.example.passtracker.data.model.UserEditEmailDTO
import com.example.passtracker.domain.model.UserEditEmail

fun UserEditEmail.toDTO(): UserEditEmailDTO {
    return UserEditEmailDTO(
        email = this.email
    )
}