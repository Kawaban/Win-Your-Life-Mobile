package com.example.winyourlife.presentation.statisticspage

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.MyHorizontalDivider
import com.example.winyourlife.presentation.customItems.MyVerticalDivider
import com.example.winyourlife.presentation.customItems.OrangeOutlinedTextField
import com.example.winyourlife.presentation.customItems.SideNavigationBar
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.ui.theme.WinYourLifeTheme

@Composable
fun StatisticsPage(navController: NavHostController, viewModel: StatisticsViewModel = hiltViewModel()) {
    WinYourLifeTheme(darkTheme = viewModel.currentUser.mapOfSettings[Settings.IS_DARK_THEME.name]
        ?.toBooleanStrictOrNull() ?: isSystemInDarkTheme()){
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
fun LandscapeLayout(navController: NavHostController, viewModel: StatisticsViewModel = hiltViewModel()) {

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

            MyHorizontalDivider()

            Text(
                text = stringResource(id = R.string.longest_streak),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OrangeOutlinedTextField("37" + " " + stringResource(id = R.string.days))

            Spacer(modifier = Modifier.height(10.dp))

            MyHorizontalDivider()

            Text(
                text = stringResource(id = R.string.tasks_completed),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OrangeOutlinedTextField("1112")

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
                text = stringResource(id = R.string.days_won),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OrangeOutlinedTextField("64")

            Spacer(modifier = Modifier.height(10.dp))

            MyHorizontalDivider()

            Text(
                text = stringResource(id = R.string.account_since),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OrangeOutlinedTextField("210" + " " + stringResource(id = R.string.days2))

            Spacer(modifier = Modifier.weight(1f))
        }

        SideNavigationBar(navController, viewModel)
    }
}

@Composable
fun PortraitLayout(navController: NavHostController, viewModel: StatisticsViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline(stringResource(id = R.string.statistics_hd))

        Spacer(modifier = Modifier.height(20.dp))

        MyHorizontalDivider()

        Text(
            text = stringResource(id = R.string.longest_streak),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OrangeOutlinedTextField("37" + " " + stringResource(id = R.string.days))

        Spacer(modifier = Modifier.height(10.dp))

        MyHorizontalDivider()

        Text(
            text = stringResource(id = R.string.tasks_completed),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OrangeOutlinedTextField("1112")

        Spacer(modifier = Modifier.height(10.dp))

        MyHorizontalDivider()

        Text(
            text = stringResource(id = R.string.days_won),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OrangeOutlinedTextField("64")

        Spacer(modifier = Modifier.height(10.dp))

        MyHorizontalDivider()

        Text(
            text = stringResource(id = R.string.account_since),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OrangeOutlinedTextField("210" + " " + stringResource(id = R.string.days2))

        Spacer(modifier = Modifier.weight(1f))

        BottomNavigationBar(navController, viewModel)
    }
}
