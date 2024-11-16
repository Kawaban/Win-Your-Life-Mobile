package com.example.winyourlife.presentation.preparenextdaypage

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.TaskList
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.MyHorizontalDivider
import com.example.winyourlife.presentation.customItems.MyVerticalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.SideNavigationBar
import com.example.winyourlife.presentation.dataObjects.TaskData
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.ui.theme.WinYourLifeTheme

@Composable
fun PrepareNextDayPage(navController: NavHostController, viewModel: PrepareNextDayViewModel = hiltViewModel()) {
    WinYourLifeTheme(darkTheme = viewModel.currentUser.mapOfSettings[Settings.IS_DARK_THEME.name]
        ?.toBooleanStrictOrNull() ?: isSystemInDarkTheme()) {
        ResponsiveLayout(navController)
    }
    BackHandler {
        viewModel.resetViewModel()
        navController.popBackStack()
    }
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
fun LandscapeLayout(navController: NavHostController, viewModel: PrepareNextDayViewModel = hiltViewModel()) {

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

            TaskList(tasks = tasks, 250)

            Button(
                onClick = {},
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .size(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onBackground),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = stringResource(id = R.string.plus_sign_description), color = MaterialTheme.colorScheme.background, fontSize = 20.sp, fontWeight = Bold)
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        MyVerticalDivider()

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            MyHorizontalDivider()

            Text(
                text = stringResource(id = R.string.prepare_day_text),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 60.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(50.dp))

            OrangeButton({ navController.navigate(NavigationScreens.MANAGE_TASKS.name) }, stringResource(id = R.string.manage_tasks_button))

            OrangeButton({ navController.navigate(NavigationScreens.CREATE_TASK.name) }, stringResource(id = R.string.create_task_button))

            Spacer(modifier = Modifier.weight(1f))
        }

        SideNavigationBar(navController, viewModel)
    }
}

@Composable
fun PortraitLayout(navController: NavHostController, viewModel: PrepareNextDayViewModel = hiltViewModel()) {

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
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline(stringResource(id = R.string.prepare_day_hd))

        Spacer(modifier = Modifier.height(20.dp))

        MyHorizontalDivider()

        Text(
            text = stringResource(id = R.string.prepare_day_text),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 60.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        TaskList(tasks = tasks, 320)

        Button(
            onClick = {},
            modifier = Modifier
                .padding(bottom = 16.dp)
                .size(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onBackground),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = stringResource(id = R.string.plus_sign_description), color = MaterialTheme.colorScheme.background, fontSize = 20.sp, fontWeight = Bold)
        }

        Spacer(modifier = Modifier.weight(1f))

        MyHorizontalDivider()

        OrangeButton({ navController.navigate(NavigationScreens.MANAGE_TASKS.name) }, stringResource(id = R.string.manage_tasks_button))

        OrangeButton({ navController.navigate(NavigationScreens.CREATE_TASK.name) }, stringResource(id = R.string.create_task_button))

        Spacer(modifier = Modifier.height(30.dp))

        BottomNavigationBar(navController, viewModel)
    }
}