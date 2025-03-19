package com.example.passtracker.app.ui.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passtracker.R
import com.example.passtracker.app.presentation.state.EditRequestState
import com.example.passtracker.app.presentation.state.ProfileState
import com.example.passtracker.app.presentation.viewmodel.EditRequestViewModel
import com.example.passtracker.app.presentation.viewmodel.ProfileViewModel
import com.example.passtracker.app.ui.component.ErrorComponent
import com.example.passtracker.app.ui.component.LoadingComponent
import com.example.passtracker.domain.model.RequestChange
import com.example.passtracker.domain.model.TypeRequest

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

    when(val state = profileState) {
        is EditRequestState.Initial -> LoadingComponent()
        is EditRequestState.Loading -> LoadingComponent()
        is EditRequestState.Success -> PassScreenContent(
            modifier = modifier,
            onBackClick = onBackClick,
            request = state.request,
            onDeletePass = { viewModel.deleteRequest(id = passId) },
            onEditPass = { updatedRequest ->
                viewModel.changeRequest(
                    request = updatedRequest,
                    id = passId
                )
            }
        )
        is EditRequestState.Failure -> ErrorComponent(state.message) {
//            viewModel.getProfile()
        }

        EditRequestState.Deleted -> Unit
    }
}


//@Preview
//@Composable
//fun PassScreenPreview() {
//    PassScreen()
//}