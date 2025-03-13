package com.example.passtracker.app.di

import com.example.passtracker.domain.usecase.CheckAuthorizationUseCase
import com.example.passtracker.domain.usecase.EditEmailUseCase
import com.example.passtracker.domain.usecase.EditPasswordUseCase
import com.example.passtracker.domain.usecase.GetProfileUseCase
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

    factory<EditEmailUseCase>{
        EditEmailUseCase(get())
    }

    factory<EditPasswordUseCase>{
        EditPasswordUseCase(get())
    }

    factory<GetProfileUseCase>{
        GetProfileUseCase(get())
    }

    factory<CheckAuthorizationUseCase>{
        CheckAuthorizationUseCase(get())
    }
}