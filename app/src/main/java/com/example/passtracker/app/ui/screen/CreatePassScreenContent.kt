package com.example.passtracker.app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.passtracker.domain.model.Request
import com.example.passtracker.domain.model.RequestChange
import com.example.passtracker.domain.model.RequestCreate

@Composable
fun CreatePassScreenContent (
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onCreatePass: (RequestCreate) -> Unit = {}
) {
    var startDate by remember { mutableStateOf("") }
    var finishDate by remember { mutableStateOf("") }
    var typeRequest by remember { mutableStateOf("") }
    var photo by remember { mutableStateOf("") }
}