package com.example.winyourlife.presentation.profilepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.MyVerticalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.SideNavigationBar
import com.example.winyourlife.presentation.customItems.WhiteOutlinedTextField

@Composable
fun ProfilePage(navController: NavHostController) {
    ResponsiveLayout(navController)
}

@Composable
fun ResponsiveLayout(navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    if (isPortrait) {
       PortraitLayout(navController)
    } else {
        LandscapeLayout(navController)
    }
}

@Composable
fun LandscapeLayout(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {

    var nickname by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

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
        }

        MyVerticalDivider()

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            OrangeButton({}, "Change avatar")

            OrangeButton({}, "Change nickname")

            OrangeButton({ navController.navigate(NavigationScreens.RESET_PASSWORD.name) }, "Change password")

            Spacer(modifier = Modifier.weight(1f))
        }

        SideNavigationBar(navController)
    }
}

@Composable
fun PortraitLayout(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {

    var nickname by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
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
