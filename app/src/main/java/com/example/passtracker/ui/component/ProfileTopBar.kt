package com.example.passtracker.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passtracker.R

@Composable
fun ProfileTopBar(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Row(modifier
        .fillMaxWidth()
        .padding(top = 24.dp)) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                painterResource(R.drawable.back_arrow_ic),
                contentDescription = null,
                tint = colorResource(R.color.white)
            )
        }
        Text(
            text = "Настройки",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp,
            color = colorResource(R.color.white),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun ProfileTopBarPreview() {
    ProfileTopBar()
}