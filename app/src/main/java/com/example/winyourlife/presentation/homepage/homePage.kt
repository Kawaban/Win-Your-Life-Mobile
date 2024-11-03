package com.example.winyourlife.presentation.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.CustomStreak
import com.example.winyourlife.presentation.customItems.TaskList
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.MyHorizontalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.dataObjects.TaskData
import com.example.winyourlife.presentation.utilScreens.LoadingScreen

@Composable
fun HomePage(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

//    HomeScreenMainContent(viewModel, navController)

    when (!viewModel.state.isLoading && !viewModel.state.isReady) {
        true -> {
            viewModel.getUserName()
        }
        false -> {
            when(viewModel.state.isLoading) {
                true -> {
                    LoadingScreen()
                }
                false -> {
                    HomeScreenMainContent(viewModel, navController)
                }
            }
        }
    }
}

@Composable
fun HomeScreenMainContent(viewModel: HomeViewModel, navController: NavHostController) {

    val tasks = listOf(
        TaskData(
            isCompleted = false,
            label = "Touch grass",
            image = R.drawable.avatar
        ),
        TaskData(
            isCompleted = true,
            label = "Touch grass",
            image = R.drawable.avatar
        ),
        TaskData(
            isCompleted = false,
            label = "Touch grass",
            image = R.drawable.avatar
        ),
        TaskData(
            isCompleted = false,
            label = "Touch grass",
            image = R.drawable.avatar
        ),
        TaskData(
            isCompleted = false,
            label = "Touch grass",
            image = R.drawable.avatar
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Headline("HELLO " + (viewModel.state.obj?.data?.name) + "!")

        Spacer(modifier = Modifier.height(20.dp))

        CustomStreak("26")

        Spacer(modifier = Modifier.height(10.dp))

        MyHorizontalDivider()

        TaskList(tasks = tasks, 250)

        Spacer(modifier = Modifier.weight(1f))

        MyHorizontalDivider()

        Spacer(modifier = Modifier.height(10.dp))

        OrangeButton({ navController.navigate(NavigationScreens.GOALS.name) }, "Your Tasks")

        OrangeButton({ navController.navigate(NavigationScreens.FRIENDS.name) }, "Your Friends")

        OrangeButton({ navController.navigate(NavigationScreens.MOTIVATION.name) }, "Motivation")

        Spacer(modifier = Modifier.height(10.dp))

        BottomNavigationBar(navController)
    }
}
