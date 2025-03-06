package com.example.passtracker.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.passtracker.R
import com.example.passtracker.ui.component.BottomSheet
import com.example.passtracker.ui.component.HeaderAppBar


@Composable
fun PassesScreenContent(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 24.dp)
    ) {
        HeaderAppBar(stringResource(R.string.my_passes))
        Image(
            painter = painterResource(R.drawable.students),
            contentDescription = null
        )
        BottomSheet(
            listOf("gfdg", "gfdg", "gfdg", "gfdg", "gfdg"),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Preview
@Composable
fun PassesScreenContentPreview() {
    PassesScreenContent()
}