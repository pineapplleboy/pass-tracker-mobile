package com.example.passtracker.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passtracker.app.presentation.state.LoginState
import com.example.passtracker.domain.model.AuthResult
import com.example.passtracker.domain.model.UserLogin
import com.example.passtracker.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Initial)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun login(user: UserLogin) {

        viewModelScope.launch {
            _state.value = LoginState.Loading

            val res = loginUseCase(user)
            if(res == AuthResult.Success) {
                _state.value = LoginState.Initial
                //Перемещаемся на экран профиля
            }
            else {
                _state.value = LoginState.Failure((res as AuthResult.Error).message)
            }
        }
    }
}