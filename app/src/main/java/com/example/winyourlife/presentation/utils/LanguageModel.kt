package com.example.winyourlife.presentation.utils

import android.app.LocaleManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.LocaleList
import com.example.winyourlife.R
import java.util.Locale

sealed class Language {

    abstract val code: String
    abstract val titleRes: Int

    companion object {

        fun setLocale(context: Context, localeCode: String) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.getSystemService(LocaleManager::class.java).applicationLocales =
                    LocaleList.forLanguageTags(localeCode)
            } else {
                saveToLocalSharedAndUpdateResources(context = context, localeTag = localeCode)
            }
        }

        private const val SHARED_PREF_SELECTED_LOCALE_KEY = "LocalePreference"

        private fun getLocaleSharedPreference(context: Context): SharedPreferences? {
            return context.applicationContext?.getSharedPreferences(
                SHARED_PREF_SELECTED_LOCALE_KEY,
                Context.MODE_PRIVATE
            )
        }

        private fun setLocaleForDevicesLowerThanTiramisu(localeTag: String, context: Context) {
            val locale = Locale(localeTag)
            Locale.setDefault(locale)
            val resources = context.resources
            val configuration = resources.configuration
            configuration.setLocale(locale)
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }

        private fun saveToLocalSharedAndUpdateResources(context: Context, localeTag: String) {
            val sharedPref = getLocaleSharedPreference(context) ?: return

            with(sharedPref.edit()) {
                putString(SHARED_PREF_SELECTED_LOCALE_KEY, localeTag)
                apply()
            }
            setLocaleForDevicesLowerThanTiramisu(localeTag, context)
        }

        fun configureLocaleOnStartForDevicesLowerThanTiramisu(context: Context) {
            setLocaleForDevicesLowerThanTiramisu(
                context = context,
                localeTag = getLocaleSharedPreference(context)?.getString(
                    SHARED_PREF_SELECTED_LOCALE_KEY,
                    English.code
                ) ?: English.code
            )
        }
    }

    data object English : Language() {
        override val code: String = "en"
        override val titleRes: Int = R.string.language_en
    }
}