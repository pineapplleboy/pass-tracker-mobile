package com.example.passtracker.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passtracker.R

@Composable
fun ProfileAvatarText(
    name: String,
    lastname: String,
    modifier: Modifier = Modifier,
    surname: String = "",
    editModeClick: () -> Unit = {}
) {
    Column(modifier.fillMaxWidth()) {
        Box(Modifier.align(Alignment.CenterHorizontally)) {
            Image(
                painterResource(R.drawable.big_account_circle),
                contentDescription = null,
                modifier = Modifier
            )
            Image(
                painterResource(R.drawable.edit_ic),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp, bottom = 8.dp)
                    .clickable {
                        editModeClick()
                    }
            )
        }

        Text(
            text = stringResource(R.string.name_text, lastname, name, surname),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 4.dp),
            style = MaterialTheme.typography.titleSmall,
            fontSize = 16.sp
        )
    }

}

@Preview
@Composable
fun ProfileAvatarTextPreview() {
    ProfileAvatarText(name = "Марат", lastname = "Бачаев")
}