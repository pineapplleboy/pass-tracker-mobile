package com.example.passtracker.data.converter

import com.example.passtracker.data.model.UserLoginDTO
import com.example.passtracker.domain.model.UserLogin

fun UserLogin.toDTO(): UserLoginDTO {
    return UserLoginDTO(
        email = this.email,
        password = this.password
    )
}