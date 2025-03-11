package com.example.passtracker.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.passtracker.R

@Composable
fun ProfileBody(modifier: Modifier = Modifier, editMode: Boolean = false, onClick: () -> Unit = {}) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .shadow(8.dp, RoundedCornerShape(12.dp))
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        ProfileTextField(
            modifier = Modifier, hint = stringResource(R.string.group),
            input = "972303"
        )
        ProfileTextField(
            modifier = Modifier, hint = stringResource(R.string.email),
            input = "mv.bachaev@gmail.com",
            editMode = editMode
        )
        ProfileTextField(
            modifier = Modifier, hint = stringResource(R.string.password),
            input = "12345678",
            editMode = editMode
        )
        Box(
            modifier = Modifier.fillMaxSize().padding(bottom = 16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (editMode) {
                ProfileButton(text = "Подтвердить", onClick = onClick)
            } else {
                Image(
                    painter = painterResource(R.drawable.tsu_icon),
                    contentDescription = null,
                    modifier = Modifier.size(112.dp)
                )
            }
        }

    }
}

@Preview
@Composable
fun ProfileBodyPreview() {
    ProfileBody()
}