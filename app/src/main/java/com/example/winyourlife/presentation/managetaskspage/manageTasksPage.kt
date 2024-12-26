package com.example.winyourlife.presentation.managetaskspage

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.EveryTaskList
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.MyHorizontalDivider
import com.example.winyourlife.presentation.customItems.MyVerticalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.SideNavigationBar
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.ui.theme.WinYourLifeTheme

@Composable
fun ManageTasksPage(navController: NavHostController, viewModel: ManageTasksViewModel = hiltViewModel()) {
    WinYourLifeTheme(darkTheme = viewModel.currentUser.mapOfSettings[Settings.IS_DARK_THEME.name]
        ?.toBooleanStrictOrNull() ?: isSystemInDarkTheme()) {
        ResponsiveLayout(navController)
    }

    LaunchedEffect(Unit) {
        viewModel.updateNavController(navController)
        viewModel.loadTasks()
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
fun LandscapeLayout(navController: NavHostController, viewModel: ManageTasksViewModel = hiltViewModel()) {

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

            EveryTaskList(viewModel, 270)

            Spacer(modifier = Modifier.weight(1f))
        }

        MyVerticalDivider()

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            OrangeButton({ navController.navigate(NavigationScreens.CREATE_TASK.name) }, stringResource(id = R.string.create_task_button))

            Spacer(modifier = Modifier.weight(1f))
        }

        SideNavigationBar(navController, viewModel)
    }
}


@Composable
fun PortraitLayout(navController: NavHostController, viewModel: ManageTasksViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline(stringResource(id = R.string.manage_tasks_hd))

        Spacer(modifier = Modifier.weight(1f))

        EveryTaskList(viewModel, 470)

        Spacer(modifier = Modifier.weight(1f))

        MyHorizontalDivider()

        OrangeButton({ navController.navigate(NavigationScreens.CREATE_TASK.name) }, stringResource(id = R.string.create_task_button))

        Spacer(modifier = Modifier.height(30.dp))

        BottomNavigationBar(navController, viewModel)
    }
}