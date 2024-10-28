package com.example.winyourlife.presentation.loginpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.navigation.NavHostController
import com.example.winyourlife.presentation.navigation.NavigationScreens

@Composable
fun loginPage(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    when (viewModel.state.isReady) {
        true -> {
            when (viewModel.state.error != null) {
                true -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Error: ${viewModel.state.error}")
                    }
                }
                false -> {
                    navController.navigate(NavigationScreens.HOME.name)
                }
            }
        }
        false -> {
            Column( modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text(text ="Login", modifier = Modifier.padding(16.dp))

                TextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.padding(16.dp))

                TextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, modifier = Modifier.padding(16.dp))

                Button(onClick = { viewModel.login(email, password) }) {
                    Text("Login")
                }

                Spacer(modifier = Modifier.padding(16.dp))
            }
        }
    }


}

