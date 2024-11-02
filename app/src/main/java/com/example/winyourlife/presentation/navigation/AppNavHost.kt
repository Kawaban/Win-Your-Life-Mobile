package com.example.winyourlife.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.winyourlife.presentation.addfriendpage.AddFriendScreen
import com.example.winyourlife.presentation.preparenextdaypage.PrepareNextDayScreen
import com.example.winyourlife.presentation.createtaskpage.CreateTaskScreen
import com.example.winyourlife.presentation.forgotpasswordpage.ForgotPasswordScreen
import com.example.winyourlife.presentation.friendspage.FriendsScreen
import com.example.winyourlife.presentation.taskspage.TasksScreen
import com.example.winyourlife.presentation.homepage.HomePage
import com.example.winyourlife.presentation.loginpage.LoginPage
import com.example.winyourlife.presentation.motivationpage.MotivationScreen
import com.example.winyourlife.presentation.notificationspage.NotificationsScreen
import com.example.winyourlife.presentation.profilepage.ProfileScreen
import com.example.winyourlife.presentation.registerpage.RegisterScreen
import com.example.winyourlife.presentation.resetpasswordpage.ResetPasswordScreen
import com.example.winyourlife.presentation.settingspage.SettingsScreen
import com.example.winyourlife.presentation.statisticspage.StatisticsScreen

//TODO prepare the next day, create new task, motivation, snack bars
//TODO task completion effects, notification classes, light theme, landscape view

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
            LoginPage(navController)
        }
        composable(NavigationScreens.HOME.name) {
            HomePage(navController)
        }
        composable(NavigationScreens.REGISTER.name) {
            RegisterScreen(navController)
        }
        composable(NavigationScreens.ADD_FRIEND.name) {
            AddFriendScreen(navController)
        }
        composable(NavigationScreens.PREPARE_NEXT_DAY.name) {
            PrepareNextDayScreen(navController)
        }
        composable(NavigationScreens.CREATE_TASK.name) {
            CreateTaskScreen(navController)
        }
        composable(NavigationScreens.FORGOT_PASSWORD.name) {
            ForgotPasswordScreen(navController)
        }
        composable(NavigationScreens.FRIENDS.name) {
            FriendsScreen(navController)
        }
        composable(NavigationScreens.GOALS.name) {
            TasksScreen(navController)
        }
        composable(NavigationScreens.MOTIVATION.name) {
            MotivationScreen(navController)
        }
        composable(NavigationScreens.NOTIFICATIONS.name) {
            NotificationsScreen(navController)
        }
        composable(NavigationScreens.PROFILE.name) {
            ProfileScreen(navController)
        }
        composable(NavigationScreens.RESET_PASSWORD.name) {
            ResetPasswordScreen(navController)
        }
        composable(NavigationScreens.SETTINGS.name) {
           SettingsScreen(navController)
        }
        composable(NavigationScreens.STATISTICS.name) {
            StatisticsScreen(navController)
        }
    }
}