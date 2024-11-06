package com.example.winyourlife.presentation

import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.winyourlife.presentation.navigation.AppNavHost
import com.example.winyourlife.presentation.utils.Language
import com.example.winyourlife.ui.theme.WinYourLifeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU)
            Language.configureLocaleOnStartForDevicesLowerThanTiramisu(this)

        enableEdgeToEdge()
        setContent {
            WinYourLifeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(navController = rememberNavController())
                }
            }
        }
    }
}
