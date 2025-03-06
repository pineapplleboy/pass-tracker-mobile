package com.example.passtracker.ui.screen

sealed class Screen (val route: String){
    object SignInScreen: Screen(route = "sign_in_screen")
    object SignUpScreen: Screen(route = "sign_up_screen")
    object PassesScreen: Screen(route = "passes_screen")
}