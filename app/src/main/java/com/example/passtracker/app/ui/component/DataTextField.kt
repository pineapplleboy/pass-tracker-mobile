package com.example.passtracker.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.passtracker.R

@Composable
fun DataTextField(modifier: Modifier = Modifier, startDate: Boolean = true, date: String = "12.06.05") {
    Column(modifier = modifier) {
        Text(
            text = if (startDate) stringResource(R.string.start_from) else stringResource(R.string.end_on),
            style = MaterialTheme.typography.labelSmall,
            color = colorResource(R.color.gray),
        )
        Text(
            text = date,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview
@Composable
fun DataTextFieldPreview(){
    DataTextField()
}