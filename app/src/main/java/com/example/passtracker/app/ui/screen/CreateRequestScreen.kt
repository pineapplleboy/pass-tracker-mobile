package com.example.passtracker.app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.passtracker.app.presentation.state.CreateRequestState
import com.example.passtracker.app.presentation.viewmodel.CreateRequestViewModel
import com.example.passtracker.app.ui.component.ErrorComponent
import com.example.passtracker.app.ui.component.LoadingComponent
import com.example.passtracker.domain.model.RequestCreate

@Composable
fun CreateRequestScreen(
    viewModel: CreateRequestViewModel,
    modifier: Modifier = Modifier
) {
    val createState by viewModel.state.collectAsState()

    when(val state = createState) {
        is CreateRequestState.Initial -> CreateRequestScreenContent(modifier) {
            viewModel.createRequest(it)
        }
        is CreateRequestState.Loading -> LoadingComponent()
        is CreateRequestState.Failure -> ErrorComponent(state.message) {
            viewModel.setInitialState()
        }
        is CreateRequestState.Success -> {
            //отправка на экран со всеми пассами
        }
    }
}

@Composable
fun CreateRequestScreenContent(
    modifier: Modifier = Modifier,
    onCreateRequest: (RequestCreate) -> Unit
) {

}