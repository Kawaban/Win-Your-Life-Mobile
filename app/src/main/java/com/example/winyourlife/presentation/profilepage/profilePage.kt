package com.example.winyourlife.presentation.profilepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.WhiteOutlinedTextField

@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {

    var nickname by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline("YOUR PROFILE")

        Spacer(modifier = Modifier.height(60.dp))

        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "User avatar",
            modifier = Modifier
                .size(128.dp)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        WhiteOutlinedTextField(nickname,{ nickname = it },"Nickname", false)

        WhiteOutlinedTextField(email,{ email = it },"Email", false)

        Spacer(modifier = Modifier.weight(1f))

        OrangeButton({}, "Change avatar")

        OrangeButton({}, "Change nickname")

        OrangeButton({ navController.navigate(NavigationScreens.RESET_PASSWORD.name) }, "Change password")

        Spacer(modifier = Modifier.weight(1f))

        BottomNavigationBar(navController)
    }
}
