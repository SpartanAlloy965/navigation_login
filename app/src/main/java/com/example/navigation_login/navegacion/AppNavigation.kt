package com.example.navigation_login.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation_login.modelos.AlumnosViewModel
import com.example.navigation_login.screens.AddScreen
import com.example.navigation_login.screens.AppAlumnos
import com.example.navigation_login.screens.LoginScreen
import com.example.navigation_login.screens.MainViewModel


@Composable
fun AppNavigation (
    isLoading: Boolean,
    envia: Boolean,
    onClick: ()-> Unit,

    ){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.LoginScreen.route,
    ){
        composable(route = AppScreens.Home.route){ AppAlumnos(navController, AlumnosViewModel()) }
        composable(route = AppScreens.AddScreen.route){ AddScreen(navController, ) }
        composable(route = AppScreens.LoginScreen.route)
        {

            if (envia) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(AppScreens.Home.route) {
                        popUpTo(AppScreens.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            } else {
                LoginScreen(
                    navController,
                    isLoading = false,
                    onLoginClick = onClick,
                )
            }
        }
    }
}
