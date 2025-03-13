package com.example.passtracker.app.di

import com.example.passtracker.app.presentation.viewmodel.LoginViewModel
import com.example.passtracker.app.presentation.viewmodel.ProfileViewModel
import com.example.passtracker.app.presentation.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<LoginViewModel> {
        LoginViewModel(get(), get())
    }

    viewModel<RegisterViewModel> {
        RegisterViewModel(get())
    }

    viewModel<ProfileViewModel> {
        ProfileViewModel(get())
    }
}