package com.example.passtracker.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passtracker.app.presentation.state.LoginState
import com.example.passtracker.app.presentation.state.RegisterState
import com.example.passtracker.domain.model.AuthResult
import com.example.passtracker.domain.model.UserLogin
import com.example.passtracker.domain.model.UserRegister
import com.example.passtracker.domain.usecase.LoginUseCase
import com.example.passtracker.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
): ViewModel() {

    private val _state = MutableStateFlow<RegisterState>(RegisterState.Initial)
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    fun register(user: UserRegister) {

        viewModelScope.launch {
            _state.value = RegisterState.Loading

            val res = registerUseCase(user)
            if(res == AuthResult.Success) {
                _state.value = RegisterState.Initial
                //Перемещаемся на экран профиля
            }
            else {
                _state.value = RegisterState.Failure((res as AuthResult.Error).message)
            }
        }
    }
}