package com.example.winyourlife.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
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
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.winyourlife.data.background.DailyReminderWorker
import com.example.winyourlife.data.network.JwtManager
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.navigation.AppNavHost
import com.example.winyourlife.presentation.utils.Language
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.ui.theme.WinYourLifeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Calendar
import java.util.concurrent.TimeUnit
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

        createNotificationChannel()


//        println("find" + WorkManager.getInstance(this).getWorkInfosByTag("dailyReminder").get())
        // check if not created
//        if(WorkManager.getInstance(this).getWorkInfosByTag("dailyReminder").get().isEmpty()) {

            val currentTime = System.currentTimeMillis()
            val midnight = Calendar.getInstance().apply {
                timeInMillis = currentTime
                set(Calendar.HOUR_OF_DAY, 22)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
                if (timeInMillis <= currentTime) {
                    add(Calendar.DAY_OF_YEAR, 1)
                }
            }.timeInMillis

            val delay = midnight - currentTime


            val uploadWorker = PeriodicWorkRequest.Builder(
                DailyReminderWorker::class.java, 24, TimeUnit.HOURS
            )
                .setInitialDelay(20000, TimeUnit.MILLISECONDS)
                .addTag("dailyReminder")
                .build()
            WorkManager.getInstance(this).enqueue(uploadWorker)

            println("Created worker")
//        }



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

    private fun createNotificationChannel() {

            val name = "CHANNEL_NAME"
            val descriptionText = "CHANNEL_DESCRIPTION"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

    }
}
