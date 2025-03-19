package com.example.passtracker.app.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passtracker.R
import com.example.passtracker.ui.component.ProfileTopBar

@Composable
fun PassTopBar(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Row(modifier
        .fillMaxWidth()
        .padding(top = 24.dp)) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                painterResource(R.drawable.back_arrow_ic),
                contentDescription = null,
                tint = colorResource(R.color.black)
            )
        }
        Text(
            text = "Пропуск",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp,
            color = colorResource(R.color.black),
            modifier = Modifier.align(Alignment.CenterVertically).weight(1f)
        )
        IconButton(
            onClick = onClick,
        ) {
            Icon(
                painterResource(R.drawable.close),
                contentDescription = null,
                tint = colorResource(R.color.black),

                )
        }
        IconButton(
            onClick = onClick,
        ) {
            Icon(
                painterResource(R.drawable.delete_ic_pass),
                contentDescription = null,
                tint = colorResource(R.color.black),

            )
        }
    }
}

@Preview
@Composable
fun ProfileTopBarPreview() {
    PassTopBar()
}