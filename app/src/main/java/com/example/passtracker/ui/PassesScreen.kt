package com.example.passtracker.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun PassesScreen(modifier: Modifier = Modifier, onProfileClicked: () -> Unit) {
    PassesScreenContent(modifier, onProfileClicked)
}