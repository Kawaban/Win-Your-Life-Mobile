package com.example.winyourlife.presentation.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.presentation.util.BottomNavigationBar
import com.example.winyourlife.presentation.util.Headline
import com.example.winyourlife.presentation.util.LoadingScreen

@Composable
fun HomePage(navController: NavHostController, viewModel: HomePageViewModel = hiltViewModel()) {

    when(!viewModel.state.isLoading && !viewModel.state.isReady) {
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
fun HomeScreenMainContent(viewModel: HomePageViewModel, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline("HELLO " + (viewModel.state.obj?.data?.name) + "!")

        Spacer(modifier = Modifier.weight(1f))
        
        BottomNavigationBar(navController = navController)
    }
}
