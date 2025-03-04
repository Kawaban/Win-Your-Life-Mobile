package com.example.winyourlife.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.winyourlife.presentation.addfriendpage.AddFriendPage
import com.example.winyourlife.presentation.createtaskpage.CreateTaskPage
import com.example.winyourlife.presentation.editTaskPage.EditTaskPage
import com.example.winyourlife.presentation.forgotpasswordpage.ForgotPasswordPage
import com.example.winyourlife.presentation.friendspage.FriendsPage
import com.example.winyourlife.presentation.homepage.HomePage
import com.example.winyourlife.presentation.loginpage.LoginPage
import com.example.winyourlife.presentation.managetaskspage.ManageTasksPage
import com.example.winyourlife.presentation.motivationpage.MotivationPage
import com.example.winyourlife.presentation.notificationspage.NotificationsPage
import com.example.winyourlife.presentation.preparenextdaypage.PrepareNextDayPage
import com.example.winyourlife.presentation.profilepage.ProfilePage
import com.example.winyourlife.presentation.registerpage.RegisterPage
import com.example.winyourlife.presentation.settingspage.SettingsPage
import com.example.winyourlife.presentation.statisticspage.StatisticsPage

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationScreens.HOME.name
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
            RegisterPage(navController)
        }
        composable(NavigationScreens.ADD_FRIEND.name) {
            AddFriendPage(navController)
        }
        composable(NavigationScreens.PREPARE_NEXT_DAY.name) {
            PrepareNextDayPage(navController)
        }
        composable(NavigationScreens.CREATE_TASK.name) {
           CreateTaskPage(navController)
        }
       composable(
            NavigationScreens.EDIT_TASK.name + "/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskIndex = backStackEntry.arguments?.getInt("index") ?: -1
            EditTaskPage(navController, taskIndex)
        }
        composable(NavigationScreens.FORGOT_PASSWORD.name) {
            ForgotPasswordPage(navController)
        }
        composable(NavigationScreens.FRIENDS.name) {
            FriendsPage(navController)
        }
        composable(NavigationScreens.MANAGE_TASKS.name) {
            ManageTasksPage(navController)
        }
        composable(NavigationScreens.MOTIVATION.name) {
            MotivationPage(navController)
        }
        composable(NavigationScreens.NOTIFICATIONS.name) {
            NotificationsPage(navController)
        }
        composable(NavigationScreens.PROFILE.name) {
            ProfilePage(navController)
        }
        composable(NavigationScreens.SETTINGS.name) {
           SettingsPage(navController)
        }
        composable(NavigationScreens.STATISTICS.name) {
            StatisticsPage(navController)
        }
    }
}