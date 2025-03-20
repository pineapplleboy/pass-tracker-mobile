package com.example.passtracker.app.di

import com.example.passtracker.domain.usecase.ChangeRequestUseCase
import com.example.passtracker.domain.usecase.CheckAuthorizationUseCase
import com.example.passtracker.domain.usecase.CreateRequestUseCase
import com.example.passtracker.domain.usecase.DeleteRequestUseCase
import com.example.passtracker.domain.usecase.EditEmailUseCase
import com.example.passtracker.domain.usecase.EditPasswordUseCase
import com.example.passtracker.domain.usecase.GetAllUserRequestUseCase
import com.example.passtracker.domain.usecase.GetProfileUseCase
import com.example.passtracker.domain.usecase.GetRequestInfoUseCase
import com.example.passtracker.domain.usecase.LoginUseCase
import com.example.passtracker.domain.usecase.LogoutUseCase
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

    factory<CreateRequestUseCase>{
        CreateRequestUseCase(get())
    }

    factory<ChangeRequestUseCase>{
        ChangeRequestUseCase(get())
    }

    factory<DeleteRequestUseCase>{
        DeleteRequestUseCase(get())
    }

    factory<GetAllUserRequestUseCase>{
        GetAllUserRequestUseCase(get())
    }

    factory<GetRequestInfoUseCase>{
        GetRequestInfoUseCase(get())
    }

    factory<LogoutUseCase>{
        LogoutUseCase(get())
    }
}