package com.example.winyourlife.presentation.goalspage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.GoalsList
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.MyHorizontalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.dataObjects.GoalData

@Composable
fun GoalsScreen(navController: NavHostController, viewModel: GoalsViewModel = hiltViewModel()) {

    val goals = listOf(
        GoalData(
            isCompleted = false,
            label = "Touch grass",
            image = R.drawable.avatar
        ),
        GoalData(
            isCompleted = true,
            label = "Touch grass",
            image = R.drawable.avatar
        ),
        GoalData(
            isCompleted = false,
            label = "Touch grass",
            image = R.drawable.avatar
        ),
        GoalData(
            isCompleted = false,
            label = "Touch grass",
            image = R.drawable.avatar
        ),
        GoalData(
            isCompleted = false,
            label = "Touch grass",
            image = R.drawable.avatar
        ),
        GoalData(
            isCompleted = false,
            label = "Touch grass",
            image = R.drawable.avatar
        ),
        GoalData(
            isCompleted = false,
            label = "Touch grass",
            image = R.drawable.avatar
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline("TODAY'S GOALS")

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Your Streak: x",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 60.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(20.dp))

        MyHorizontalDivider()

        Text(
            text = "To win the day you need to complete x more tasks",
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 60.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(20.dp))

        GoalsList(goals = goals, 320)

        Spacer(modifier = Modifier.weight(1f))

        OrangeButton({ navController.navigate(NavigationScreens.CONFIGURE_TASKS.name) }, "Prepare the Next Day")

        Spacer(modifier = Modifier.height(40.dp))

        BottomNavigationBar(navController)
    }
}
