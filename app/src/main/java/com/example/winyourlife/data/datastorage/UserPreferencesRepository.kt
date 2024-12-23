package com.example.winyourlife.data.datastorage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.winyourlife.domain.UserPreferencesRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject




class MyUserPreferencesRepository @Inject constructor(
    private val userDataStorePreferences: DataStore<Preferences>
) : UserPreferencesRepository {

    override suspend fun setParameter(
        name: String,
        value: String
    ) {
        Result.runCatching {
            userDataStorePreferences.edit { preferences ->
                preferences[stringPreferencesKey(name)] = value
            }
        }
    }

    override suspend fun getParameter(name: String): Result<String> {
        return Result.runCatching {
            val flow = userDataStorePreferences.data
                .catch { exception ->
                    /*
                     * dataStore.data throws an IOException when an error
                     * is encountered when reading data
                     */
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    // Get our name value, defaulting to "" if not set
                    preferences[stringPreferencesKey(name)]
                }
            val value = flow.firstOrNull() ?: "" // we only care about the 1st value
            value
        }
    }

}