package com.example.passtracker.app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.passtracker.app.presentation.viewmodel.CreateRequestViewModel
import com.example.passtracker.app.presentation.viewmodel.EditRequestViewModel
import com.example.passtracker.app.presentation.viewmodel.LoginViewModel
import com.example.passtracker.app.presentation.viewmodel.PassesViewModel
import com.example.passtracker.app.presentation.viewmodel.ProfileViewModel
import com.example.passtracker.app.presentation.viewmodel.RegisterViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.SignInScreen.route
    ) {
        composable(Screen.SignInScreen.route) {

            val viewModel = koinViewModel<LoginViewModel>()

            SignInScreen(
                viewModel = viewModel,
                onClicked = {
                    navController.navigate(Screen.SignUpScreen.route) {
                        popUpTo(Screen.SignUpScreen.route) {
                            inclusive = true
                        }
                    }
                }, clickNext = {
                    navController.navigate(Screen.PassesScreen.route)
                })
        }

        composable(Screen.SignUpScreen.route) {

            val viewModel = koinViewModel<RegisterViewModel>()

            SignUpScreen(
                viewModel = viewModel,
                onClicked = {
                    navController.navigate(Screen.SignInScreen.route) {
                        popUpTo(Screen.SignInScreen.route) {
                            inclusive = true
                        }
                    }
                },
                clickNext = {
                    navController.navigate(Screen.ProfileScreen.route) {
                        popUpTo(Screen.ProfileScreen.route) {
                            inclusive = true
                        }
                    }
                })
        }
        composable(Screen.PassesScreen.route) {

            val viewModel = koinViewModel<PassesViewModel>()

            PassesScreen(
                viewModel,
                onProfileClicked = {
                    navController.navigate(Screen.ProfileScreen.route)
                },
                onItemSelected = { passId ->
                    navController.navigate(Screen.EditPassScreen.createRoute(passId))
                },
                onAddItemSelected = {
                    navController.navigate(Screen.CreateScreen.route)
                },
            )
        }
        composable(Screen.ProfileScreen.route) {

            val viewModel = koinViewModel<ProfileViewModel>()

            ProfileScreen(
                onBackClick = {
                    navController.navigate(Screen.PassesScreen.route) {
                        popUpTo(Screen.PassesScreen.route) {
                            inclusive = true
                        }
                    }
                },
                viewModel = viewModel
            )
        }
        composable(Screen.EditPassScreen.route) { backStackEntry ->
            val viewModel = koinViewModel<EditRequestViewModel>()
            val passId = backStackEntry.arguments?.getString("id") ?: return@composable
            PassScreen(
                onBackClick = {
                    navController.navigate(Screen.PassesScreen.route) {
                        popUpTo(Screen.PassesScreen.route) {
                            inclusive = true
                        }
                    }
                },
                viewModel = viewModel,
                passId = passId
            )
        }
        composable(Screen.CreateScreen.route) {
            val viewModel = koinViewModel<CreateRequestViewModel>()
            CreatePassScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                viewModel = viewModel,
                goOnPassScreen = {
                    navController.navigate(Screen.PassesScreen.route) {
                        popUpTo(Screen.PassesScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

    }
}