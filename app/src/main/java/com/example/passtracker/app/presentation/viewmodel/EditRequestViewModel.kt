package com.example.passtracker.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passtracker.app.presentation.state.EditRequestState
import com.example.passtracker.domain.model.RequestChange
import com.example.passtracker.domain.usecase.ChangeRequestUseCase
import com.example.passtracker.domain.usecase.DeleteRequestUseCase
import com.example.passtracker.domain.usecase.GetRequestInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditRequestViewModel(
    private val changeRequestUseCase: ChangeRequestUseCase,
    private val getRequestUseCase: GetRequestInfoUseCase,
    private val deleteRequestUseCase: DeleteRequestUseCase
): ViewModel() {

    private val _state = MutableStateFlow<EditRequestState>(EditRequestState.Initial)
    val state: StateFlow<EditRequestState> = _state.asStateFlow()


    fun changeRequest(request: RequestChange, id: String) {
        _state.value = EditRequestState.Loading
        viewModelScope.launch {
            val result = changeRequestUseCase(id, request)
            result.fold(
                onSuccess = {
                    getRequest(id)
                },
                onFailure = {
                    _state.value = EditRequestState.Failure(it.message)
                }
            )
        }
    }

    fun deleteRequest(id: String) {
        _state.value = EditRequestState.Loading
        viewModelScope.launch {
            val result = deleteRequestUseCase(id)
            result.fold(
                onSuccess = {
                    _state.value = EditRequestState.Deleted
                },
                onFailure = {
                    _state.value = EditRequestState.Failure(it.message)
                }
            )
        }
    }

    fun getRequest(id: String) {
        _state.value = EditRequestState.Loading

        viewModelScope.launch {
            val result = getRequestUseCase(id)
            result.fold(
                onSuccess = {
                    _state.value = EditRequestState.Success(it)
                },
                onFailure = {
                    _state.value = EditRequestState.Failure(it.message)
                }
            )
        }
    }
}