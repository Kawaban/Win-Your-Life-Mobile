package com.example.winyourlife.presentation.customItems
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.os.LocaleListCompat
import com.example.winyourlife.R
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.presentation.settingspage.SettingsViewModel
import com.example.winyourlife.presentation.utils.Language

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageDropDownMenu(viewModel: SettingsViewModel, context: Context, currentLocale: MutableState<Language>) {
//    val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("en")
//    AppCompatDelegate.setApplicationLocales(appLocale)

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
                                //currentLocale.value = Language.convertStringToLanguage(options[index], context)
                                selectedOption = options[index]
                                Language.setLocale(context = context, localeCode = Language.convertStringToLanguage(options[index], context).code)
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
}