package com.example.passtracker.app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.passtracker.app.presentation.state.CreateRequestState
import com.example.passtracker.app.presentation.state.EditRequestState
import com.example.passtracker.app.presentation.viewmodel.CreateRequestViewModel
import com.example.passtracker.app.ui.component.ErrorComponent
import com.example.passtracker.app.ui.component.LoadingComponent

@Composable
fun CreatePassScreen (
    viewModel: CreateRequestViewModel,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
    ) {
        val passState by viewModel.state.collectAsState()

        when(val state = passState) {
            is CreateRequestState.Initial -> LoadingComponent()
            is CreateRequestState.Loading -> LoadingComponent()
            is CreateRequestState.Success -> CreatePassScreenContent(
                modifier = modifier,
                onBackClick = onBackClick,
                onCreatePass = { updatedRequest ->
                    viewModel.createRequest(
                        request = updatedRequest,
                    )
                }
            )
            is CreateRequestState.Failure -> ErrorComponent(state.message) {
//            viewModel.getProfile()
            }
        }
    }