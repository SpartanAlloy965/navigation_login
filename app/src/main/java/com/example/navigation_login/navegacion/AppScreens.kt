package com.example.navigation_login.navegacion

sealed class AppScreens (val route: String){
    object Alumnos_card: AppScreens("Alumnos_Card")
    object Home : AppScreens(route = "Home")
    object AddScreen : AppScreens(route = "AddScreen")
    object LoginScreen : AppScreens(route = "LoginScreen")
    object Principal : AppScreens(route = "principal")
}
