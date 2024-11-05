package com.example.winyourlife.presentation.utils

import android.content.Context
import android.content.res.Configuration
import android.os.LocaleList
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.winyourlife.R
import java.util.Locale


    fun SetLocale(language: String, context: Context) {

        val mapOfLanguageCodes = mapOf(
            context.getString(R.string.language_en) to "en",
            context.getString(R.string.language_pl) to "pl",
        )


        val locale = Locale(mapOfLanguageCodes[language] ?: "en")
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocales(LocaleList(locale))

        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }