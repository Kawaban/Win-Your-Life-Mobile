package com.example.winyourlife.presentation.motivationpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun MotivationPage(navController: NavHostController) {
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
fun LandscapeLayout(navController: NavHostController, viewModel: MotivationViewModel = hiltViewModel()) {

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
                    text = stringResource(id = R.string.motivation1),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(0.7f)
                        .align(Alignment.CenterStart)
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        SideNavigationBar(navController)
    }
}


@Composable
fun PortraitLayout(navController: NavHostController, viewModel: MotivationViewModel = hiltViewModel()) {

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
                text = stringResource(id = R.string.motivation1),
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
        }

        BottomNavigationBar(navController)
    }
}
