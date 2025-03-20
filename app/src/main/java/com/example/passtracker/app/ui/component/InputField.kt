package com.example.passtracker.app.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passtracker.R

@Composable
fun InputField(
    hint: String,
    iconId: Int,
    value: String,
    modifier: Modifier = Modifier,
    isPasswordField: Boolean = false,
    onValueChange: (String) -> Unit,

    ) {
    var passwordVisible by remember { mutableStateOf(!isPasswordField) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10))
            .background(color = colorResource(R.color.light_gray)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = hint,
                    color = colorResource(R.color.gray),

                    )
            },
            modifier = Modifier.weight(1f)
        )

        Image(painter = when {
            isPasswordField && passwordVisible -> painterResource(R.drawable.eye_on)
            isPasswordField && !passwordVisible -> painterResource(iconId)
            else -> painterResource(iconId)
        },
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary),
            modifier = if (!isPasswordField) Modifier
                .padding(end = 16.dp) else Modifier
                .padding(end = 16.dp)
                .clickable { passwordVisible = !passwordVisible })

    }
}

//@Preview
//@Composable
//fun Preview() {
//    InputField(
//        hint = "Негры",
//        iconId = R.drawable.ic_launcher_background
//    )
//}