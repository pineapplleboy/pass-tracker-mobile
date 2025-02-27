package com.example.passtracker

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.passtracker.ui.screen.Screen
import com.example.passtracker.ui.SignUpScreen
import com.example.passtracker.ui.SignInScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.SignInScreen.route) {
        composable(Screen.SignInScreen.route) {
            SignInScreen(modifier = modifier, onClicked = {
                navController.navigate(Screen.SignUpScreen.route) {
                    popUpTo(Screen.SignUpScreen.route) {
                        inclusive = true
                    }
                }
            },)
        }

        composable(Screen.SignUpScreen.route) {
            SignUpScreen(modifier = modifier, onClicked = {
                navController.navigate(Screen.SignInScreen.route) {
                    popUpTo(Screen.SignInScreen.route) {
                        inclusive = true
                    }
                }
            },)
        }

    }
}