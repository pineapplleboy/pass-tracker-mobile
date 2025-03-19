package com.example.passtracker.app.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.passtracker.R
import com.example.passtracker.domain.model.RequestShort
import com.example.passtracker.app.ui.component.BottomSheet
import com.example.passtracker.ui.component.HeaderAppBar


@Composable
fun PassesScreenContent(
    requests: List<RequestShort>,
    modifier: Modifier = Modifier,
    onProfileClicked: () -> Unit = {},
    onItemSelected: (String) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()

    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painterResource(R.drawable.students),
                contentDescription = null,
                modifier = Modifier.padding(top = 32.dp)
            )
            Column {
                HeaderAppBar(stringResource(R.string.my_passes), modifier, onProfileClicked)

            }
            BottomSheet(
                requests,
                modifier = Modifier.padding(horizontal = 8.dp).padding(top = 8.dp),
                onItemSelected = onItemSelected
            )
            FloatingActionButton(
                onClick = {},
                modifier = Modifier.align(Alignment.BottomEnd).padding(24.dp),
                containerColor = colorResource(R.color.red),
                contentColor = colorResource(R.color.bottom_background)

            ) {
                Icon(Icons.Filled.Add, null, modifier = Modifier.size(32.dp))
            }
        }
    }
}
//
//@Preview
//@Composable
//fun PassesScreenContentPreview() {
//    PassesScreenContent()
//}