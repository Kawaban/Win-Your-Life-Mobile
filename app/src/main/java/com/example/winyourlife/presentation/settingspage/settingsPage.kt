package com.example.winyourlife.presentation.settingspage

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.MyHorizontalDivider
import com.example.winyourlife.presentation.customItems.MySwitch
import com.example.winyourlife.presentation.customItems.MyVerticalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.SideNavigationBar
import com.example.winyourlife.ui.theme.WinYourLifeTheme
import com.example.winyourlife.presentation.customItems.LanguageDropDownMenu
import com.example.winyourlife.presentation.utils.Language

@Composable
fun SettingsPage(navController: NavHostController, viewModel: SettingsViewModel = hiltViewModel()) {
    ResponsiveLayout(navController)
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
fun LandscapeLayout(navController: NavHostController, viewModel: SettingsViewModel = hiltViewModel()) {

    val initialDarkTheme = isSystemInDarkTheme()

    val isDarkTheme = remember {
        mutableStateOf(
            viewModel.currentUser.userData?.mapOfSettings?.get(Settings.IS_DARK_THEME.name)?.toBooleanStrictOrNull() ?: initialDarkTheme)
    }

    val isDailyReminders = remember {
        mutableStateOf(viewModel.currentUser.userData?.mapOfSettings?.get(Settings.IS_DAILY_REMINDER.name)?.toBooleanStrictOrNull() ?: true)
    }

    val isFriendsNotifications = remember {
        mutableStateOf(viewModel.currentUser.userData?.mapOfSettings?.get(Settings.IS_FRIENDS_NOTIFICATION.name)?.toBooleanStrictOrNull() ?: true)
    }

    val context = LocalContext.current
    val currentLocale = remember { mutableStateOf(Language.getCurrentLanguage(context)) }


    WinYourLifeTheme(darkTheme = isDarkTheme.value) {

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
                    text = stringResource(id = R.string.notifications_options),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Row(
                    modifier = Modifier
                        .width(280.dp)
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.friends_notifications_switch),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    MySwitch(isFriendsNotifications.value) { isFriendsNotifications.value = it; viewModel.saveSettings(
                        Settings.IS_FRIENDS_NOTIFICATION.name,it.toString()) }
                }

                Row(
                    modifier = Modifier
                        .width(280.dp)
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.daily_reminders_switch),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    MySwitch(isDailyReminders.value) { isDailyReminders.value = it; viewModel.saveSettings(
                        Settings.IS_DAILY_REMINDER.name,it.toString()) }
                }

                MyHorizontalDivider()

                Row(
                    modifier = Modifier
                        .width(280.dp)
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.dark_theme_switch),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    MySwitch(isDarkTheme.value) { isDarkTheme.value = it; viewModel.saveSettings(
                        Settings.IS_DARK_THEME.name,it.toString()) }
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

                LanguageDropDownMenu(viewModel, context, currentLocale)

                Spacer(modifier = Modifier.weight(0.8f))

                MyHorizontalDivider()

                Spacer(modifier = Modifier.weight(1f))

                OrangeButton(
                    { navController.navigate(NavigationScreens.STATISTICS.name) }, stringResource(id = R.string.statistics_button)
                )

                OrangeButton({ navController.navigate(NavigationScreens.LOGIN.name) }, stringResource(id = R.string.log_out_button))

                Spacer(modifier = Modifier.weight(1f))
            }

            SideNavigationBar(navController, viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortraitLayout(navController: NavHostController, viewModel: SettingsViewModel = hiltViewModel()) {

    val initialDarkTheme = isSystemInDarkTheme()

    val isDarkTheme = remember {
        mutableStateOf(
            viewModel.currentUser.userData?.mapOfSettings?.get(Settings.IS_DARK_THEME.name)?.toBooleanStrictOrNull() ?: initialDarkTheme)
    }

    val isDailyReminders = remember {
        mutableStateOf(viewModel.currentUser.userData?.mapOfSettings?.get(Settings.IS_DAILY_REMINDER.name)?.toBooleanStrictOrNull() ?: true)
    }

    val isFriendsNotifications = remember {
        mutableStateOf(viewModel.currentUser.userData?.mapOfSettings?.get(Settings.IS_FRIENDS_NOTIFICATION.name)?.toBooleanStrictOrNull() ?: true)
    }

    val context = LocalContext.current
    val currentLocale = remember { mutableStateOf(Language.getCurrentLanguage(context)) }


    WinYourLifeTheme(darkTheme = isDarkTheme.value) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Headline(stringResource(id = R.string.settings_hd))

            Spacer(modifier = Modifier.height(20.dp))

            MyHorizontalDivider()

            Text(
                text = stringResource(id = R.string.notifications_options),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Row(
                modifier = Modifier
                    .width(280.dp)
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.friends_notifications_switch),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                MySwitch(isFriendsNotifications.value) { isFriendsNotifications.value = it; viewModel.saveSettings(
                    Settings.IS_FRIENDS_NOTIFICATION.name,it.toString()) }
            }

            Row(
                modifier = Modifier
                    .width(280.dp)
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.daily_reminders_switch),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                MySwitch(isDailyReminders.value) { isDailyReminders.value = it; viewModel.saveSettings(
                    Settings.IS_DAILY_REMINDER.name,it.toString()) }
            }

            MyHorizontalDivider()

            Row(
                modifier = Modifier
                    .width(280.dp)
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.dark_theme_switch),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                MySwitch(isDarkTheme.value) { isDarkTheme.value = it; viewModel.saveSettings(
                    Settings.IS_DARK_THEME.name,it.toString()) }
            }

            MyHorizontalDivider()

            val options =
                listOf(stringResource(id = R.string.language_en), stringResource(id = R.string.language_pl))
            var expanded by remember { mutableStateOf(false) }
            var selectedOption by remember { mutableStateOf(viewModel.currentUser.userData?.mapOfSettings?.get(
                Settings.APPLICATION_LANGUAGE.name) ?: options[0]) }

            Row(
                modifier = Modifier
                    .width(280.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.app_language),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Column(
                    modifier = Modifier
                        .width(145.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        TextField(
                            readOnly = true,
                            value = selectedOption,
                            onValueChange = {},
                            modifier = Modifier
                                .weight(1f)
                                .menuAnchor(),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = stringResource(id = R.string.expand_description),
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.weight(0.1f)
                                )
                            },

                            colors = TextFieldDefaults.colors(
                                focusedTextColor = MaterialTheme.colorScheme.secondary,
                                unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                                focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                                unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.background)
                                .width(145.dp)
                        ) {
                            options.forEachIndexed { index, text ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = text,
                                            color = MaterialTheme.colorScheme.secondary,
                                            style = MaterialTheme.typography.bodyLarge,
                                        )
                                    },
                                    onClick = {
                                        if(selectedOption != options[index]){
                                            viewModel.saveSettings(Settings.APPLICATION_LANGUAGE.name, options[index])
                                        }
                                        currentLocale.value = Language.convertStringToLanguage(options[index], context)
                                        Language.setLocale(context = context, localeCode = Language.convertStringToLanguage(options[index], context).code)
                                        selectedOption = options[index]
                                        expanded = false
                                    },
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.onBackground)
                                        .width(280.dp),
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                )
                            }
                        }
                    }
                }
            }

            MyHorizontalDivider()

            Spacer(modifier = Modifier.weight(1f))

            OrangeButton({ navController.navigate(NavigationScreens.STATISTICS.name) }, stringResource(id = R.string.statistics_button))

            OrangeButton({viewModel.logOut(); navController.navigate(NavigationScreens.LOGIN.name) }, stringResource(id = R.string.log_out_button))

            Spacer(modifier = Modifier.height(10.dp))

            BottomNavigationBar(navController, viewModel)
        }
    }
}
