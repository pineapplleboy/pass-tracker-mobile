package com.example.passtracker.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.passtracker.R

@Composable
fun TextTip(text: String, pointedText: String, onClicked: () -> Unit = {}, modifier: Modifier = Modifier){
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(R.color.black)
        )
        Text(
            text = pointedText,
            style = MaterialTheme.typography.bodyLarge,
            color = colorResource(R.color.red),
            modifier = Modifier.clickable(onClick = onClicked)
        )
    }
}