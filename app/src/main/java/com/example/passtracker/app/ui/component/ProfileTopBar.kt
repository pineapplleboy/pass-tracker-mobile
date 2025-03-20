package com.example.passtracker.ui.component

import androidx.compose.foundation.clickable
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
fun ProfileTopBar(modifier: Modifier = Modifier, onClick: () -> Unit = {}, onLogout: () -> Unit = {}) {
    Row(modifier
        .fillMaxWidth()
        ) {
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
            modifier = Modifier.align(Alignment.CenterVertically).weight(1f)
        )
        Icon(
            painterResource(R.drawable.logout_bottom_ic),
            contentDescription = null,
            tint = colorResource(R.color.white),
            modifier = Modifier.padding(end = 24.dp).align(Alignment.CenterVertically).clickable {
                onLogout()
            }
        )
    }
}

@Preview
@Composable
fun ProfileTopBarPreview() {
    ProfileTopBar()
}