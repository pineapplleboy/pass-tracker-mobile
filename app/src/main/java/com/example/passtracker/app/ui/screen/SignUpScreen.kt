package com.example.passtracker.app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.passtracker.R
import com.example.passtracker.app.presentation.state.LoginState
import com.example.passtracker.app.presentation.state.RegisterState
import com.example.passtracker.app.presentation.viewmodel.LoginViewModel
import com.example.passtracker.app.presentation.viewmodel.RegisterViewModel
import com.example.passtracker.app.ui.component.AppButton
import com.example.passtracker.app.ui.component.ErrorComponent
import com.example.passtracker.app.ui.component.InputField
import com.example.passtracker.app.ui.component.LoadingComponent
import com.example.passtracker.app.ui.component.TextTip
import com.example.passtracker.app.ui.component.TitleField
import com.example.passtracker.domain.model.UserRegister

@Composable
fun SignUpScreen(
    viewModel: RegisterViewModel,
    modifier: Modifier = Modifier,
    onClicked: () -> Unit
) {
    val registerState by viewModel.state.collectAsState()

    when (val state = registerState) {
        is RegisterState.Initial -> SignUpScreenContent(
            modifier = modifier,
            onClicked = {
                onClicked()
            },
            onRegister = {
                viewModel.register(it)
            }
        )

        is RegisterState.Loading -> LoadingComponent()

        is RegisterState.Failure -> ErrorComponent(state.message) {
        }
    }
}

@Composable
fun SignUpScreenContent(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit = {},
    onRegister: (UserRegister) -> Unit
){
    var firstName by remember { mutableStateOf("") }
    var secondName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var group by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmation by remember { mutableStateOf("") }

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
            value = secondName,
            modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp)
        ) {
            secondName = it
        }
        InputField(
            hint = stringResource(R.string.name),
            iconId = R.drawable.ic_person,
            value = firstName,
            modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp)
        ) {
            firstName = it
        }
        InputField(
            hint = stringResource(R.string.surname),
            iconId = R.drawable.ic_person,
            value = middleName,
            modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp)
        ) {
            middleName = it
        }
        InputField(
            hint = stringResource(R.string.email),
            iconId = R.drawable.ic_email,
            value = email,
            modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp)
        ) {
            email = it
        }
        InputField(
            hint = stringResource(R.string.group),
            iconId = R.drawable.ic_person,
            value = group,
            modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp)
        ) {
            group = it
        }
        InputField(
            hint = stringResource(R.string.password),
            iconId = R.drawable.ic_eye,
            value = password,
            modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp)
        ) {
            password = it
        }
        InputField(
            hint = stringResource(R.string.confirm_password),
            iconId = R.drawable.ic_eye,
            value = confirmation,
            modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp)
        ) {
            confirmation = it
        }

    }
    Column (modifier
        .fillMaxSize()
        .padding(bottom = 32.dp),verticalArrangement = Arrangement.Bottom){
        AppButton(text = stringResource(R.string.next), {
            onRegister(UserRegister(
                secondName = secondName,
                firstName = firstName,
                middleName = middleName,
                group = group.toInt(),
                email = email,
                password = password
            ))
        })
        Spacer(modifier = Modifier.size(12.dp))
        TextTip(text = stringResource(R.string.already_member), pointedText = stringResource(R.string.enter), onClicked)
    }
}

//@Preview
//@Composable
//fun PreviewSignUp() {
//    SignUpScreen()
//}