package com.example.passtracker.data.converter

import com.example.passtracker.data.model.UserRegisterDTO
import com.example.passtracker.domain.model.UserRegister

fun UserRegister.toDTO(): UserRegisterDTO {
    return UserRegisterDTO(
        secondName = this.secondName,
        firstName = this.firstName,
        middleName = this.middleName,
        group = this.group,
        email = this.email,
        password = this.password
    )
}