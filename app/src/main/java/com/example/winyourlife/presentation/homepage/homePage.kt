package com.example.winyourlife.presentation.homepage

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.CustomStreak
import com.example.winyourlife.presentation.customItems.TodayTaskList
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.MyHorizontalDivider
import com.example.winyourlife.presentation.customItems.MyVerticalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.SideNavigationBar
import com.example.winyourlife.presentation.utilScreens.LoadingScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.ui.theme.WinYourLifeTheme
import kotlinx.coroutines.delay
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Size
import nl.dionsegijn.konfetti.core.Position
import java.util.concurrent.TimeUnit

@Composable
fun HomePage(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    BackHandler {
        viewModel.resetViewModel()
        navController.popBackStack()
    }

    WinYourLifeTheme(darkTheme = viewModel.currentUser.mapOfSettings[Settings.IS_DARK_THEME.name]
        ?.toBooleanStrictOrNull() ?: isSystemInDarkTheme()) {
        when (!viewModel.state.isLoading && !viewModel.state.isReady) {
            true -> {
                viewModel.getUserName()
            }

            false -> {
                when (viewModel.state.isLoading) {
                    true -> {
                        LoadingScreen()
                    }

                    false -> {
                        when (viewModel.state.error == null) {
                            true -> {
                                ResponsiveLayout(viewModel, navController)
                            }

                            false -> {
                                viewModel.resetViewModel()
                                navController.navigate(NavigationScreens.LOGIN.name)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ResponsiveLayout(viewModel: HomeViewModel, navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    if (isPortrait) {
        PortraitLayout(viewModel, navController)
    } else {
        LandscapeLayout(viewModel, navController)
    }
}

@Composable
fun LandscapeLayout(viewModel: HomeViewModel, navController: NavHostController) {

    val context = LocalContext.current

    val konfettiPartyLeft = Party(
        emitter = Emitter(duration = 1, TimeUnit.SECONDS)
            .max(200),
        position = Position.Relative(0.0, 0.0),
        speed = 40f,
        spread = 360,
        size = listOf(Size.SMALL, Size.LARGE)
    )

    val konfettiPartyRight = Party(
        emitter = Emitter(duration = 1, TimeUnit.SECONDS)
            .max(200),
        position = Position.Relative(1.0, 0.0),
        speed = 40f,
        spread = 360,
        size = listOf(Size.SMALL, Size.LARGE)
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
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

                TodayTaskList(viewModel, 250)

                Spacer(modifier = Modifier.weight(1f))
            }

            MyVerticalDivider()

            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))

                CustomStreak("26")

                Spacer(modifier = Modifier.weight(1f))

                MyHorizontalDivider()

                Spacer(modifier = Modifier.weight(1f))

                OrangeButton({ viewModel.resetViewModel(); navController.navigate(NavigationScreens.PREPARE_NEXT_DAY.name) }, stringResource(id = R.string.prepare_day_button))

                OrangeButton({viewModel.resetViewModel(); navController.navigate(NavigationScreens.FRIENDS.name) },
                    stringResource(id = R.string.your_friends_button))

                OrangeButton({viewModel.resetViewModel(); navController.navigate(NavigationScreens.MOTIVATION.name) },
                    stringResource(id = R.string.motivation_button))

                Spacer(modifier = Modifier.weight(1f))
            }

            SideNavigationBar(navController, viewModel)
        }

        if (viewModel.dayCompleted) {
            viewModel.playAudio(context)
            KonfettiView(
                modifier = Modifier.fillMaxSize(),
                parties = listOf(konfettiPartyLeft, konfettiPartyRight)
            )
            LaunchedEffect(key1 = viewModel.dayCompleted) {
                delay(4000)
                viewModel.stopAudio()
            }
        }
    }
}

@Composable
fun PortraitLayout(viewModel: HomeViewModel, navController: NavHostController) {

    val context = LocalContext.current

    val konfettiPartyLeft = Party(
        emitter = Emitter(duration = 1, TimeUnit.SECONDS)
            .max(200),
        position = Position.Relative(0.0, 0.0),
        speed = 40f,
        spread = 360,
        size = listOf(Size.SMALL, Size.LARGE)
    )

    val konfettiPartyRight = Party(
        emitter = Emitter(duration = 1, TimeUnit.SECONDS)
            .max(200),
        position = Position.Relative(1.0, 0.0),
        speed = 40f,
        spread = 360,
        size = listOf(Size.SMALL, Size.LARGE)
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Headline(
                stringResource(id = R.string.home_hd1) +" "+ (viewModel.state.obj?.data?.name) + "!"
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomStreak(viewModel.currentUser.userData?.streak.toString())

            Spacer(modifier = Modifier.height(10.dp))

            MyHorizontalDivider()

            TodayTaskList(viewModel, 250)

            Spacer(modifier = Modifier.weight(1f))

            MyHorizontalDivider()

            Spacer(modifier = Modifier.height(10.dp))

            OrangeButton({ viewModel.resetViewModel(); navController.navigate(NavigationScreens.PREPARE_NEXT_DAY.name) }, stringResource(id = R.string.prepare_day_button))

            OrangeButton(
                {viewModel.resetViewModel(); navController.navigate(NavigationScreens.FRIENDS.name) },
                stringResource(id = R.string.your_friends_button)
            )

            OrangeButton(
                {viewModel.resetViewModel(); navController.navigate(NavigationScreens.MOTIVATION.name) },
                stringResource(id = R.string.motivation_button)
            )

            Spacer(modifier = Modifier.height(10.dp))

            BottomNavigationBar(navController, viewModel)
        }

        if (viewModel.dayCompleted) {
            viewModel.playAudio(context)
            KonfettiView(
                modifier = Modifier.fillMaxSize(),
                parties = listOf(konfettiPartyLeft, konfettiPartyRight)
            )
            LaunchedEffect(key1 = viewModel.dayCompleted) {
                delay(4000)
                viewModel.stopAudio()
            }
        }
    }
}
