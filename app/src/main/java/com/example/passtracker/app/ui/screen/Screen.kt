package com.example.passtracker.app.ui.screen

sealed class Screen (val route: String){
    object SignInScreen: Screen(route = "sign_in_screen")
    object SignUpScreen: Screen(route = "sign_up_screen")

}