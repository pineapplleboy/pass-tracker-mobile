package com.example.passtracker.app.di

import com.example.passtracker.domain.usecase.LoginUseCase
import com.example.passtracker.domain.usecase.RegisterUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<LoginUseCase>{
        LoginUseCase(get())
    }

    factory<RegisterUseCase>{
        RegisterUseCase(get())
    }
}