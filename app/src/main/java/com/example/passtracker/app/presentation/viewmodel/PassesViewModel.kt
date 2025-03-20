package com.example.passtracker.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passtracker.app.presentation.state.PassesState
import com.example.passtracker.domain.model.RequestShort
import com.example.passtracker.domain.usecase.GetAllUserRequestUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PassesViewModel(
    private val getAllUserRequestUseCase: GetAllUserRequestUseCase
): ViewModel() {

    private val _state = MutableStateFlow<PassesState>(PassesState.Initial)
    val state: StateFlow<PassesState> = _state.asStateFlow()

    private var currentPage = 1
    private val currentRequests = mutableListOf<RequestShort>()
    private val pageSize = 1000

    fun loadAllPasses() {
        currentPage = 1
        currentRequests.clear()
        loadNextPage()
    }

    fun loadNextPage() {
        viewModelScope.launch {
            _state.value = PassesState.Loading

            val result = getAllUserRequestUseCase(size = pageSize, page = currentPage)
            result.fold(
                onSuccess = { pagedList ->
                    pagedList.requests?.let { currentRequests.addAll(it.toList()) }
                    _state.value = PassesState.Success(requests = currentRequests.toList())
                    currentPage++
                },
                onFailure = {
                    _state.value = PassesState.Failure(message = it.message)
                }
            )
        }
    }
}