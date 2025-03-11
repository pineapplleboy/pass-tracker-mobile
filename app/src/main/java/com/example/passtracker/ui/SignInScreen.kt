package com.example.passtracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.passtracker.R
import com.example.passtracker.ui.component.AppButton
import com.example.passtracker.ui.component.InputField
import com.example.passtracker.ui.component.TextTip
import com.example.passtracker.ui.component.TitleField

@Composable
fun SignInScreen(modifier: Modifier = Modifier, onClicked: () -> Unit = {}, clickNext: () -> Unit = {}){
    Column (
        modifier.fillMaxSize().background(color = Color.White),
        verticalArrangement = Arrangement.Center
    ){
        TitleField(
            loginText = stringResource(R.string.login),
            accessText = stringResource(R.string.enter_for_access)
        )
        InputField(
            hint = stringResource(R.string.email),
            iconId = R.drawable.ic_email,
            modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp)
        )
        InputField(
            hint = stringResource(R.string.password),
            iconId = R.drawable.ic_eye,
            modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp)
        )

    }
    Column (modifier.fillMaxSize().padding(bottom = 32.dp),verticalArrangement = Arrangement.Bottom){
        AppButton(text = stringResource(R.string.next), clickNext)
        Spacer(modifier = Modifier.size(12.dp))
        TextTip(text = stringResource(R.string.not_register), pointedText = stringResource(R.string.to_sign_up), onClicked = onClicked)
    }
}

@Preview
@Composable
fun Preview() {
    SignInScreen()
}