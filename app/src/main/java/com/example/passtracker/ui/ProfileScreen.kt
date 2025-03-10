package com.example.passtracker.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}) {
    ProfileScreenContent(modifier, onBackClick = onBackClick)
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}
