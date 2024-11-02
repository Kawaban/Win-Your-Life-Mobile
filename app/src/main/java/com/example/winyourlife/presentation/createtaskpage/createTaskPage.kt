package com.example.winyourlife.presentation.createtaskpage

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
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.MyHorizontalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.WhiteOutlinedTextField

@Composable
fun CreateTaskScreen(navController: NavHostController, viewModel: CreateTaskViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline("CREATE A NEW TASK")

        Spacer(modifier = Modifier.height(30.dp))

        MyHorizontalDivider()

        WhiteOutlinedTextField("", {}, "Task name", true)

        MyHorizontalDivider()

        OrangeButton({},"Pick an image")

        Spacer(modifier = Modifier.weight(1f))

        MyHorizontalDivider()

        OrangeButton({},"Save the Task")

        Spacer(modifier = Modifier.height(30.dp))

        BottomNavigationBar(navController)
    }
}