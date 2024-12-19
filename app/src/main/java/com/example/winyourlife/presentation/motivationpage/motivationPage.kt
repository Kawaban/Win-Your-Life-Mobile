package com.example.winyourlife.presentation.motivationpage

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.SideNavigationBar
import com.example.winyourlife.presentation.customItems.VideoPlayerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.winyourlife.presentation.utils.PreferencesManager
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.ui.theme.WinYourLifeTheme

@Composable
fun MotivationPage(navController: NavHostController, viewModel: MotivationViewModel = hiltViewModel()) {

    val showVideoDialog by viewModel.showVideoDialog.collectAsState()
    val startPlaybackPosition by viewModel.startPlaybackPosition.collectAsState()
    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }

    WinYourLifeTheme(darkTheme = viewModel.currentUser.mapOfSettings[Settings.IS_DARK_THEME.name]
        ?.toBooleanStrictOrNull() ?: isSystemInDarkTheme()) {
        ResponsiveLayout(
            navController = navController,
            showVideoDialog = showVideoDialog,
            startPlaybackPosition = startPlaybackPosition,
            onShowVideoDialogChange = { viewModel.updateShowVideoDialog(it) },
            onPlaybackPositionChange = { viewModel.updatePlaybackPosition(it) }
        )
    }

    BackHandler {
        viewModel.resetViewModel()
        navController.popBackStack()
    }

    LaunchedEffect(Unit) {
        viewModel.randomizeMotivation()
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetViewModel()
            if (viewModel.isPlaying()) {
                viewModel.stopAudio()
            }
        }
    }
}

@Composable
fun ResponsiveLayout(
    navController: NavHostController,
    showVideoDialog: Boolean,
    startPlaybackPosition: Long,
    onShowVideoDialogChange: (Boolean) -> Unit,
    onPlaybackPositionChange: (Long) -> Unit
) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    if (isPortrait) {
        PortraitLayout(
            navController = navController,
            showVideoDialog = showVideoDialog,
            startPlaybackPosition = startPlaybackPosition,
            onShowVideoDialogChange = onShowVideoDialogChange,
            onPlaybackPositionChange = onPlaybackPositionChange
        )
    } else {
        LandscapeLayout(
            navController = navController,
            showVideoDialog = showVideoDialog,
            startPlaybackPosition = startPlaybackPosition,
            onShowVideoDialogChange = onShowVideoDialogChange,
            onPlaybackPositionChange = onPlaybackPositionChange
        )
    }
}

@Composable
fun LandscapeLayout(
    navController: NavHostController,
    viewModel: MotivationViewModel = hiltViewModel(),
    showVideoDialog: Boolean,
    startPlaybackPosition: Long,
    onShowVideoDialogChange: (Boolean) -> Unit,
    onPlaybackPositionChange: (Long) -> Unit
) {
    val context = LocalContext.current
    val randomMotivation by viewModel.randomMotivation.collectAsState()

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

            Box(
                modifier = Modifier
                    .fillMaxSize(0.85f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tate),
                    contentDescription = stringResource(id = R.string.motivation_image_description),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .aspectRatio(0.6f)
                        .align(Alignment.BottomCenter)
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxSize(0.85f)
            ) {

                Text(
                    text = randomMotivation?.let { context.getString(it.first) } ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.7f)
                        .align(Alignment.CenterStart)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.3f)
                        .align(Alignment.BottomStart)
                ) {

                    Spacer(Modifier.width(10.dp))

                    Card(
                        onClick = {
                            randomMotivation?.second?.let { mp3ResId ->
                                viewModel.playAudio(context, mp3ResId)
                            }
                        },
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier.size(60.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(15.dp)
                                )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.talk),
                                contentDescription = stringResource(id = R.string.talk_description),
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier
                                    .size(40.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }

                    Spacer(Modifier.width(10.dp))

                    Card(
                        onClick = {
                            if (viewModel.isPlaying()) {
                                viewModel.stopAudio()
                            }
                            onShowVideoDialogChange(true)
                        },
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier.size(60.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(15.dp)
                                )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.movie),
                                contentDescription = stringResource(id = R.string.movie_description),
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier
                                    .size(40.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }

                    Spacer(Modifier.width(10.dp))
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        SideNavigationBar(navController, viewModel)
    }

    if (showVideoDialog) {
        VideoPlayerDialog(
            width = 720,
            height = 250,
            startPlaybackPosition = startPlaybackPosition,
            onPlaybackPositionChange = { newPosition ->
                viewModel.updatePlaybackPosition(newPosition)
            },
            onDismiss = {
                onShowVideoDialogChange(false)
                onPlaybackPositionChange(0L)
            }
        )
    }
}

@Composable
fun PortraitLayout(
    navController: NavHostController,
    viewModel: MotivationViewModel = hiltViewModel(),
    showVideoDialog: Boolean,
    startPlaybackPosition: Long,
    onShowVideoDialogChange: (Boolean) -> Unit,
    onPlaybackPositionChange: (Long) -> Unit
) {

    val context = LocalContext.current
    val randomMotivation by viewModel.randomMotivation.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline(stringResource(id = R.string.motivation_hd))

        Spacer(modifier = Modifier.weight(0.1f))

        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = randomMotivation?.let { context.getString(it.first) } ?: "",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth(0.7f)
                    .align(Alignment.TopEnd)
            )

            Image(
                painter = painterResource(id = R.drawable.tate),
                contentDescription = stringResource(id = R.string.motivation_image_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(0.6f)
                    .align(Alignment.BottomStart)
            )

            Column(
                modifier = Modifier
                    .height(200.dp)
                    .width(100.dp)
                    .align(Alignment.CenterEnd),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Card(
                    onClick = {
                        randomMotivation?.second?.let { mp3ResId ->
                            viewModel.playAudio(context, mp3ResId)
                        }
                    },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.size(60.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(15.dp))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.talk),
                            contentDescription = stringResource(id = R.string.talk_description),
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.Center)
                        )
                    }
                }

                Card(
                    onClick = {
                        if (viewModel.isPlaying()) {
                            viewModel.stopAudio()
                        }
                        onShowVideoDialogChange(true)
                    },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.size(60.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(15.dp))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.movie),
                            contentDescription = stringResource(id = R.string.movie_description),
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }

        BottomNavigationBar(navController, viewModel)
    }

    if (showVideoDialog) {
        VideoPlayerDialog(
            width = 420,
            height = 250,
            startPlaybackPosition = startPlaybackPosition,
            onPlaybackPositionChange = { newPosition ->
                viewModel.updatePlaybackPosition(newPosition)
            },onDismiss = {
                onShowVideoDialogChange(false)
                onPlaybackPositionChange(0L)
            }
        )
    }
}
