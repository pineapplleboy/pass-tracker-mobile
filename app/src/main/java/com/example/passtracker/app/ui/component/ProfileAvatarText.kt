package com.example.passtracker.app.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passtracker.R

@Composable
fun ProfileAvatarText(
    name: String,
    modifier: Modifier = Modifier,
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
            text = name,
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
    ProfileAvatarText(name = "Марат")
}