package com.example.passtracker.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passtracker.app.presentation.state.LoginState
import com.example.passtracker.domain.model.AuthResult
import com.example.passtracker.domain.model.UserLogin
import com.example.passtracker.domain.usecase.CheckAuthorizationUseCase
import com.example.passtracker.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    checkAuthorizationUseCase: CheckAuthorizationUseCase
): ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Initial)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    init {
        if(checkAuthorizationUseCase()) {
            _state.value = LoginState.Success
        }
    }

    fun login(user: UserLogin) {

        viewModelScope.launch {
            _state.value = LoginState.Loading

            val res = loginUseCase(user)
            if(res == AuthResult.Success) {
                _state.value = LoginState.Success
            }
            else {
                _state.value = LoginState.Failure((res as AuthResult.Error).message)
            }
        }
    }

    fun setInitialState() {
        _state.value = LoginState.Initial
    }
}