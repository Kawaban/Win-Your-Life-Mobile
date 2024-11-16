package com.example.winyourlife.presentation.customItems

import android.content.Context
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
import androidx.compose.ui.res.stringResource
import com.example.winyourlife.R
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.presentation.settingspage.SettingsViewModel
import com.example.winyourlife.presentation.utils.Language

fun mapOptionToLanguageCode(index: Int): String {
    return when (index) {
        0 -> "en"
        1 -> "pl"
        else -> "en"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageDropDownMenu(viewModel: SettingsViewModel, context: Context) {

    val options = listOf(
        stringResource(id = R.string.language_en),
        stringResource(id = R.string.language_pl)
    )

    var expanded by remember { mutableStateOf(false) }

    var selectedOptionIndex by remember {
        mutableStateOf(
            when (viewModel.currentUser.mapOfSettings[Settings.APPLICATION_LANGUAGE.name]) {
                "en" -> 0
                "pl" -> 1
                else -> 0
            }
        )
    }

    var selectedOption by remember { mutableStateOf(options[selectedOptionIndex]) }

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
                    onValueChange = { },
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
                                if (selectedOptionIndex != index) {
                                    val languageCode = mapOptionToLanguageCode(index)

                                    viewModel.saveSettings(
                                        Settings.APPLICATION_LANGUAGE.name,
                                        languageCode
                                    )

                                    selectedOptionIndex = index
                                    selectedOption = options[index]

                                    Language.setLocale(
                                        context = context,
                                        localeCode = languageCode
                                    )
                                }
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