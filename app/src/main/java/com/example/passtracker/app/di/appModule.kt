package com.example.passtracker.app.di

import com.example.passtracker.app.presentation.viewmodel.CreateRequestViewModel
import com.example.passtracker.app.presentation.viewmodel.EditRequestViewModel
import com.example.passtracker.app.presentation.viewmodel.LoginViewModel
import com.example.passtracker.app.presentation.viewmodel.PassesViewModel
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

    viewModel<PassesViewModel> {
        PassesViewModel(get())
    }

    viewModel<CreateRequestViewModel> {
        CreateRequestViewModel(get())
    }

    viewModel<EditRequestViewModel> {
        EditRequestViewModel(
            get(),
            get(),
            get()
        )
    }
}