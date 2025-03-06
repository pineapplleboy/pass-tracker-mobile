package com.example.passtracker.app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.passtracker.app.presentation.viewmodel.LoginViewModel
import com.example.passtracker.app.presentation.viewmodel.RegisterViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.SignInScreen.route) {
        composable(Screen.SignInScreen.route) {

            val viewModel = koinViewModel<LoginViewModel>()

            SignInScreen(
                modifier = modifier,
                viewModel = viewModel,
                onClicked = {
                navController.navigate(Screen.SignUpScreen.route) {
                    popUpTo(Screen.SignUpScreen.route) {
                        inclusive = true
                    }
                }
            },)
        }

        composable(Screen.SignUpScreen.route) {

            val viewModel = koinViewModel<RegisterViewModel>()

            SignUpScreen(
                modifier = modifier,
                viewModel = viewModel,
                onClicked = {
                navController.navigate(Screen.SignInScreen.route) {
                    popUpTo(Screen.SignInScreen.route) {
                        inclusive = true
                    }
                }
            },)
        }

    }
}