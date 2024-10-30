package com.example.winyourlife.presentation.addfriendpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.presentation.util.BottomNavigationBar
import com.example.winyourlife.presentation.util.Headline
import com.example.winyourlife.presentation.util.MyHorizontalDivider
import com.example.winyourlife.presentation.util.OrangeButton
import com.example.winyourlife.presentation.util.WhiteOutlinedTextField

@Composable
fun AddFriendScreen(navController: NavHostController, viewModel: AddFriendViewModel = hiltViewModel()) {

    var email by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline("ADD NEW FRIEND")

        Spacer(modifier = Modifier.height(50.dp))

        MyHorizontalDivider()

        Text(
            text = "Enter the email address of the person you want to invite to your friends",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 60.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.weight(0.3f))

        WhiteOutlinedTextField(email,{ email = it },"Email")

        Spacer(modifier = Modifier.height(30.dp))

        OrangeButton({}, "Send invitation")

        Spacer(modifier = Modifier.weight(1f))

        MyHorizontalDivider()

        Spacer(modifier = Modifier.height(70.dp))

        BottomNavigationBar(navController)
    }
}
