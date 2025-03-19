package com.example.passtracker.app.ui.screen

sealed class Screen (val route: String){
    object SignInScreen: Screen(route = "sign_in_screen")
    object SignUpScreen: Screen(route = "sign_up_screen")
    object PassesScreen: Screen(route = "passes_screen")
    object ProfileScreen: Screen(route = "profile_screen")
    object EditPassScreen: Screen(route = "pass_edit_screen/{id}") {
        fun createRoute(id: String) = "pass_edit_screen/$id"
    }
    object CreateScreen: Screen(route = "create_pass_screen")
}