package com.example.passtracker.app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.passtracker.R
import com.example.passtracker.domain.model.Profile
import com.example.passtracker.app.ui.component.ProfileAvatarText
import com.example.passtracker.app.ui.component.ProfileBody
import com.example.passtracker.ui.component.ProfileTopBar

@Composable
fun ProfileScreenContent(
    profile: Profile,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    var editMode by remember { mutableStateOf(false) }
    Column(
        modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.red))
    ) {
        ProfileTopBar(modifier = Modifier.padding(top = 24.dp), onBackClick, onLogoutClick)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 78.dp)
                .background(
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    color = colorResource(R.color.white)
                )
        ) {
            ProfileAvatarText(
                name = profile.name,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .offset(y = (-60).dp),
                editModeClick = { editMode = true }
            )
            ProfileBody(
                modifier = Modifier.padding(horizontal = 24.dp),
                editMode = editMode,
                onClick = { editMode = false },
                profile = profile
            )

        }
    }
}

//@Preview
//@Composable
//fun ProfileScreenContentPreview() {
//    ProfileScreenContent()
//}