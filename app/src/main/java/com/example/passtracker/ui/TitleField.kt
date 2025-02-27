package com.example.passtracker.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passtracker.R

@Composable
fun TitleField(
    loginText: String,
    accessText: String,
    modifier: Modifier = Modifier
) {
    var columnHeight by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    Row(modifier = modifier.padding(horizontal = 24.dp)) {
        Image(
            painter = painterResource(R.drawable.tsu_icon),
            contentDescription = null,
            modifier = Modifier
                .width(columnHeight)
                .height(columnHeight)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .onSizeChanged { size ->
                    columnHeight = with(density) { size.height.toDp() }
                }
                .padding(start = 8.dp)
        ) {
            Text(
                text = loginText,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = accessText,
                style = MaterialTheme.typography.bodySmall,
                color = colorResource(R.color.light_black)
            )
        }
    }
}

