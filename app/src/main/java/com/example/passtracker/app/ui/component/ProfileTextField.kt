package com.example.passtracker.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passtracker.R

@Composable
fun ProfileTextField(
    modifier: Modifier = Modifier,
    hint: String,
    input: String,
    editMode: Boolean = false
) {
    var value by remember { mutableStateOf(input) }
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = hint,
            style = MaterialTheme.typography.labelSmall,
            color = colorResource(R.color.dark_gray),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.size(8.dp))
        TextField(
            value = value,
            onValueChange = {
                value = it
            },
            enabled = editMode,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                disabledTextColor = Color.Black,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.labelMedium,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, shape = RoundedCornerShape(16.dp), color = Color.Gray),

            )
    }
}

@Preview
@Composable
fun ProfileTextFieldPreview() {
    ProfileTextField(hint = "Почта", input = "Бачаев")
}