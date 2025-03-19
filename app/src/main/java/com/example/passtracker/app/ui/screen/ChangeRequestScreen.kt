package com.example.passtracker.app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.passtracker.app.presentation.state.EditRequestState
import com.example.passtracker.app.presentation.viewmodel.EditRequestViewModel
import com.example.passtracker.app.ui.component.ErrorComponent
import com.example.passtracker.app.ui.component.LoadingComponent
import com.example.passtracker.domain.model.Request
import com.example.passtracker.domain.model.RequestChange

//@Composable
//fun ChangeRequestScreen(
//    viewModel: EditRequestViewModel,
//    modifier: Modifier = Modifier
//) {
//    val editState by viewModel.state.collectAsState()
//
//    when(val state = editState) {
//        is EditRequestState.Initial -> LoadingComponent()
//        is EditRequestState.Loading -> LoadingComponent()
//
//        is EditRequestState.Failure -> ErrorComponent(state.message) {
//            viewModel.getRequest()
//        }
//
//        is EditRequestState.Success -> ChangeRequestScreenContent(
//            request = state.request
//        ) {
//            viewModel.changeRequest(it)
//        }
//
//        is EditRequestState.Deleted -> {
//            //Переход на экран со всеми пассами
//        }
//    }
//}

@Composable
fun ChangeRequestScreenContent(
    request: Request,
    modifier: Modifier = Modifier,
    onChange: (RequestChange) -> Unit
) {

}