package com.example.passtracker.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.passtracker.R
import com.example.passtracker.ui.component.BottomSheet
import com.example.passtracker.ui.component.HeaderAppBar


@Composable
fun PassesScreenContent(modifier: Modifier = Modifier, onProfileClicked: () -> Unit = {}) {

    Column(
        modifier = modifier
            .fillMaxSize()

    ) {
        Box(modifier = Modifier){
            Image(
                painter = painterResource(R.drawable.students),
                contentDescription = null,
                modifier = Modifier.padding(top = 32.dp)
            )
            Column {
                HeaderAppBar(stringResource(R.string.my_passes), modifier, onProfileClicked)

            }
            BottomSheet(
                listOf("gfdg", "gfdg", "gfdg", "gfdg", "gfdg"),
                modifier = Modifier.padding(horizontal = 8.dp).padding(top = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun PassesScreenContentPreview() {
    PassesScreenContent()
}