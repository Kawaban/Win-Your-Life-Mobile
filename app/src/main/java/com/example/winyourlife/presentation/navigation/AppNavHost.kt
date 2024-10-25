package com.example.winyourlife.presentation.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.winyourlife.presentation.homepage.homePage
import com.example.winyourlife.presentation.loginpage.loginPage

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationScreens.LOGIN.name,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationScreens.LOGIN.name) {
            loginPage(navController)
        }
        composable(NavigationScreens.HOME.name) {
            homePage(navController)
        }
    }
}