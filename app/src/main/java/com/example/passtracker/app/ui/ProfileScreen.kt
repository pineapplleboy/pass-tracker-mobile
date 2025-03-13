package com.example.passtracker.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.passtracker.app.presentation.state.ProfileState
import com.example.passtracker.app.presentation.viewmodel.ProfileViewModel
import com.example.passtracker.app.ui.component.ErrorComponent
import com.example.passtracker.app.ui.component.LoadingComponent

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    val profileState by viewModel.state.collectAsState()

    when(val state = profileState) {
        is ProfileState.Initial -> LoadingComponent()
        is ProfileState.Loading -> LoadingComponent()
        is ProfileState.Success -> ProfileScreenContent(
            modifier = modifier,
            onBackClick = onBackClick,
            profile = state.profile
        )
        is ProfileState.Failure -> ErrorComponent(state.message) {
            viewModel.getProfile()
        }
    }
}
//
//@Preview
//@Composable
//fun ProfileScreenPreview() {
//    ProfileScreen()
//}
