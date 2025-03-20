package com.example.passtracker.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passtracker.app.presentation.state.ProfileState
import com.example.passtracker.domain.usecase.GetAllUserRequestUseCase
import com.example.passtracker.domain.usecase.GetProfileUseCase
import com.example.passtracker.domain.usecase.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val logoutUseCase: LogoutUseCase
): ViewModel() {

    private val _state = MutableStateFlow<ProfileState>(ProfileState.Initial)
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    init {
        getProfile()
    }

    fun getProfile() {
        viewModelScope.launch {
            _state.value = ProfileState.Loading

            val res = getProfileUseCase()
            if(res.isSuccess) {
                val body = res.getOrNull()
                if(body != null) {
                    _state.value = ProfileState.Success(body)
                }
                else {
                    _state.value = ProfileState.Failure("Empty body")
                }
            }
            else {
                _state.value = ProfileState.Failure(res.exceptionOrNull()?.message)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _state.value = ProfileState.Loading

            val res = logoutUseCase()
            if(res.isSuccess) {
                val body = res.getOrNull()
                if(body != null) {
                    getProfile()
                }
                else {
                    _state.value = ProfileState.Failure("Empty body")
                }
            }
            else {
                _state.value = ProfileState.Failure(res.exceptionOrNull()?.message)
            }
        }
    }
}