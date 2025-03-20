package com.example.passtracker.app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.passtracker.app.presentation.state.EditRequestState
import com.example.passtracker.app.presentation.viewmodel.EditRequestViewModel
import com.example.passtracker.app.ui.component.ErrorComponent
import com.example.passtracker.app.ui.component.LoadingComponent

@Composable
fun PassScreen(
    viewModel: EditRequestViewModel,
    modifier: Modifier = Modifier,
    passId: String,
    onBackClick: () -> Unit = {}
) {
    val profileState by viewModel.state.collectAsState()

    LaunchedEffect(passId) {
        viewModel.getRequest(passId)
    }

    when (val state = profileState) {
        is EditRequestState.Initial -> LoadingComponent()
        is EditRequestState.Loading -> LoadingComponent()
        is EditRequestState.Success -> PassScreenContent(
            modifier = modifier,
            onBackClick = onBackClick,
            request = state.request,
            onDeletePass = {
                viewModel.deleteRequest(id = passId)
            },
            onEditPass = { updatedRequest ->
                viewModel.changeRequest(
                    request = updatedRequest,
                    id = passId
                )
            }
        )

        is EditRequestState.Failure -> ErrorComponent(state.message) {
            viewModel.getRequest(passId)
        }

        EditRequestState.Deleted -> LaunchedEffect(Unit) {
            onBackClick()
        }
    }
}


//@Preview
//@Composable
//fun PassScreenPreview() {
//    PassScreen()
//}