package com.example.passtracker.app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.passtracker.app.presentation.state.PassesState
import com.example.passtracker.app.presentation.viewmodel.PassesViewModel
import com.example.passtracker.app.ui.component.ErrorComponent
import com.example.passtracker.app.ui.component.LoadingComponent


@Composable
fun PassesScreen(
    viewModel: PassesViewModel,
    modifier: Modifier = Modifier,
    onProfileClicked: () -> Unit,
    onItemSelected: (String) -> Unit,
    onAddItemSelected: () -> Unit
) {
    val passesState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadAllPasses()
    }

    when(val state = passesState) {
        is PassesState.Initial -> LoadingComponent()
        is PassesState.Loading -> LoadingComponent()
        is PassesState.Failure -> ErrorComponent(state.message) {

        }
        is PassesState.Success -> PassesScreenContent(state.requests, modifier, onProfileClicked, onItemSelected = onItemSelected, onAddItemSelected)
    }
}