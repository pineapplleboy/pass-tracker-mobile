package com.example.passtracker.app.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.unit.dp
import com.example.passtracker.R

@Composable
fun TypeDataField (
    hint: String,
    iconId: Int,
    value: String,
    modifier: Modifier = Modifier,
    editMode: Boolean = true,
    onValueChange: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    val typeOptions = listOf("Учебная деятельность", "По болезни", "По семейным обстоятельствам")
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
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
                enabled = false,
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

            Image(
                painter = painterResource(iconId),
                contentDescription = null,
                modifier = if (editMode) Modifier
                    .size(36.dp)
                    .padding(end = 12.dp).clickable {
                        expanded = true
                    } else  Modifier
                    .size(36.dp)
                    .padding(end = 12.dp),
                colorFilter = if (editMode) null else ColorFilter.tint(colorResource(R.color.dark_gray))
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth().padding(horizontal = 24.dp)

        ) {
            typeOptions.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option, style = MaterialTheme.typography.bodySmall) },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
