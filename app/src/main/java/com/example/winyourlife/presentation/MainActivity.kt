package com.example.winyourlife.presentation

import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.winyourlife.data.network.JwtManager
import com.example.winyourlife.domain.UserPreferencesRepository
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.navigation.AppNavHost
import com.example.winyourlife.presentation.utils.Language
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.ui.theme.WinYourLifeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var jwtManager: JwtManager

    @Inject
    lateinit var currentUser: CurrentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU)
            Language.configureLocaleOnStartForDevicesLowerThanTiramisu(this)

        runBlocking{
            launch {
                jwtManager.setJwtFromCache()
                currentUser.setSettings()
            }
        }

        enableEdgeToEdge()
        setContent {
            WinYourLifeTheme(darkTheme = currentUser.mapOfSettings[Settings.IS_DARK_THEME.name]
                ?.toBooleanStrictOrNull() ?: isSystemInDarkTheme()){
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(navController = rememberNavController())
                }
            }
        }
    }
}
