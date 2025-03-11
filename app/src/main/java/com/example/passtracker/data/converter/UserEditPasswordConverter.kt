package com.example.passtracker.data.converter

import com.example.passtracker.data.model.UserEditPasswordDTO
import com.example.passtracker.domain.model.UserEditPassword

fun UserEditPassword.toDTO(): UserEditPasswordDTO {
    return UserEditPasswordDTO(
        password = this.password
    )
}