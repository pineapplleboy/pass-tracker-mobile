package com.example.passtracker.app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.passtracker.app.presentation.viewmodel.LoginViewModel
import com.example.passtracker.app.ui.component.AppButton
import com.example.passtracker.app.ui.component.ErrorComponent
import com.example.passtracker.app.ui.component.InputField
import com.example.passtracker.app.ui.component.LoadingComponent
import com.example.passtracker.app.ui.component.TextTip
import com.example.passtracker.app.ui.component.TitleField
import com.example.passtracker.domain.model.UserLogin

@Composable
fun SignInScreen(
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier,
    onClicked: () -> Unit,
    clickNext: () -> Unit
) {
    val loginState by viewModel.state.collectAsState()
    LaunchedEffect(loginState) {
        if (loginState is LoginState.Success) {
            clickNext()
        }
    }
    when (val state = loginState) {
        is LoginState.Initial -> SignInScreenContent(
            modifier = modifier,
            onClicked = {
                onClicked()
            },
            onLogin = {
                viewModel.login(it)
            },

        )

        is LoginState.Loading -> LoadingComponent()

        is LoginState.Failure -> ErrorComponent(state.message) {
        }
        is LoginState.Success -> Unit

    }
}

@Composable
fun SignInScreenContent(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit = {},
    onLogin: (user: UserLogin) -> Unit = {},
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        TitleField(
            loginText = stringResource(R.string.login),
            accessText = stringResource(R.string.enter_for_access)
        )
        InputField(
            hint = stringResource(R.string.email),
            iconId = R.drawable.ic_email,
            value = email,
            modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp)
        ) {
            email = it
        }
        InputField(
            hint = stringResource(R.string.password),
            iconId = R.drawable.ic_eye,
            value = password,
            modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp)
        ) {
            password = it
        }
    }

    Column(
        modifier
            .fillMaxSize()
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        AppButton(text = stringResource(R.string.next), {
            onLogin(UserLogin(email, password))
        })
        Spacer(modifier = Modifier.size(12.dp))
        TextTip(
            text = stringResource(R.string.not_register),
            pointedText = stringResource(R.string.to_sign_up),
            onClicked = onClicked
        )
    }
}

@Preview
@Composable
fun Preview() {
    SignInScreenContent()
}