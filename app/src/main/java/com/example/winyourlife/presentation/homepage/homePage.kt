package com.example.winyourlife.presentation.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun homePage(navController: NavHostController, viewModel: HomePageViewModel = hiltViewModel()) {

    val state by viewModel.state

    when (state.isReady) {
        true -> {
            when (state.error != null) {
                true -> {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Error: ${viewModel.state.error}")
                    }
                }
                false -> {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Hello, ${viewModel.state.obj?.data?.name}")
                    }
                }
            }
        }
        false -> {

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.padding(16.dp))
                Text("Loading...")
                Spacer(modifier = Modifier.padding(16.dp))
                Button ( onClick = { viewModel.getUserName() }) {
                    Text("Get User Name")
                }
            }
        }
    }
}