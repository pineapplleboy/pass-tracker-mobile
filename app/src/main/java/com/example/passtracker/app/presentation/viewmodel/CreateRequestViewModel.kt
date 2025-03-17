package com.example.passtracker.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passtracker.app.presentation.state.CreateRequestState
import com.example.passtracker.domain.model.RequestCreate
import com.example.passtracker.domain.usecase.CreateRequestUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateRequestViewModel(
    private val createRequestUseCase: CreateRequestUseCase
): ViewModel() {

    private val _state = MutableStateFlow<CreateRequestState>(CreateRequestState.Initial)
    val state: StateFlow<CreateRequestState> = _state.asStateFlow()

    fun createRequest(request: RequestCreate) {
        _state.value = CreateRequestState.Loading

        viewModelScope.launch {
            val result = createRequestUseCase(request)
            result.fold(
                onSuccess = {
                    _state.value = CreateRequestState.Success
                },
                onFailure = {
                    _state.value = CreateRequestState.Failure(it.message)
                }
            )
        }
    }
}