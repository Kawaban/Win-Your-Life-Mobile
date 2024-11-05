package com.example.winyourlife.domain

interface UserPreferencesRepository {

    suspend fun setParameter(
        name: String,
        value: String
    )

    suspend fun getParameter(name: String): Result<String>
}