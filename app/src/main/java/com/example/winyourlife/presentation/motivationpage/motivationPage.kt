package com.example.winyourlife.presentation.motivationpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.util.Headline

@Composable
fun MotivationScreen(navController: NavHostController, viewModel: MotivationViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline("MOTIVATION")

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Nothing in life is free.",
            fontSize = 18.sp,
            color = Color.White,
            lineHeight = 24.sp,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            style = TextStyle(
                fontWeight = FontWeight.Normal
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.tate),
            contentDescription = "Motivational Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .aspectRatio(0.75f)
        )
    }
}
