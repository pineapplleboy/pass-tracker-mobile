package com.example.passtracker.app.di

import com.example.passtracker.app.presentation.viewmodel.LoginViewModel
import com.example.passtracker.app.presentation.viewmodel.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<LoginViewModel> {
        LoginViewModel(get())
    }

    viewModel<RegisterViewModel> {
        RegisterViewModel(get())
    }
}