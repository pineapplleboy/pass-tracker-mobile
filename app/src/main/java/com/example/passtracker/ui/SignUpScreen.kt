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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.passtracker.R
import com.example.passtracker.ui.component.AppButton
import com.example.passtracker.ui.component.InputField
import com.example.passtracker.ui.component.TextTip
import com.example.passtracker.ui.component.TitleField

@Composable
fun SignUpScreen(modifier: Modifier = Modifier){
    Column(
        modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 40.dp),
        verticalArrangement = Arrangement.Top
    ){
        TitleField(
            loginText = stringResource(R.string.sign_up),
            accessText = stringResource(R.string.sign_up_tip)
        )
        InputField(
            hint = stringResource(R.string.lastname),
            iconId = R.drawable.ic_person,
            modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp)
        )
        InputField(
            hint = stringResource(R.string.name),
            iconId = R.drawable.ic_person,
            modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp)
        )
        InputField(
            hint = stringResource(R.string.surname),
            iconId = R.drawable.ic_person,
            modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp)
        )
        InputField(
            hint = stringResource(R.string.email),
            iconId = R.drawable.ic_email,
            modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp)
        )
        InputField(
            hint = stringResource(R.string.group),
            iconId = R.drawable.ic_person,
            modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp)
        )
        InputField(
            hint = stringResource(R.string.password),
            iconId = R.drawable.ic_eye,
            modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp)
        )
        InputField(
            hint = stringResource(R.string.confirm_password),
            iconId = R.drawable.ic_eye,
            modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp)
        )

    }
    Column (modifier
        .fillMaxSize()
        .padding(bottom = 32.dp),verticalArrangement = Arrangement.Bottom){
        AppButton(text = stringResource(R.string.next), {})
        Spacer(modifier = Modifier.size(12.dp))
        TextTip(text = stringResource(R.string.already_member), pointedText = stringResource(R.string.enter))
    }
}

@Preview
@Composable
fun PreviewSignUp() {
    SignUpScreen()
}