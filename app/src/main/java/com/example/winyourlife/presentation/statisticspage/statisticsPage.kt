package com.example.winyourlife.presentation.statisticspage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.presentation.util.BottomNavigationBar
import com.example.winyourlife.presentation.util.Headline
import com.example.winyourlife.presentation.util.MyHorizontalDivider
import com.example.winyourlife.presentation.util.OrangeOutlinedTextField

@Composable
fun StatisticsScreen(navController: NavHostController, viewModel: StatisticsViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline("STATISTICS")

        Spacer(modifier = Modifier.height(20.dp))

        MyHorizontalDivider()

        Text(
            text = "Longest streak",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Light
        )

        OrangeOutlinedTextField("37 days")

        Spacer(modifier = Modifier.height(10.dp))

        MyHorizontalDivider()

        Text(
            text = "Number of tasks completed",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Light
        )

        OrangeOutlinedTextField("1112")

        Spacer(modifier = Modifier.height(10.dp))

        MyHorizontalDivider()

        Text(
            text = "Number of friends",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Light
        )

        OrangeOutlinedTextField("4")

        Spacer(modifier = Modifier.height(10.dp))

        MyHorizontalDivider()

        Text(
            text = "You've been with us for ...",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Light
        )

        OrangeOutlinedTextField("210 days!")

        Spacer(modifier = Modifier.weight(1f))

        BottomNavigationBar(navController)
    }
}
