package com.example.winyourlife.data.network

import com.example.winyourlife.domain.UserPreferencesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JwtManager @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) {
    private var jwt: String? = null

    suspend fun setJwt(jwt: String) {
        userPreferencesRepository.setParameter("token",jwt)
        this.jwt = "Bearer $jwt"
    }

    fun getJwt(): String? {
        return jwt
    }

    fun isJwtSet(): Boolean {
        return jwt != null && jwt != "Bearer "
    }

    suspend fun resetJwt() {
        jwt = null
        userPreferencesRepository.setParameter("token","")
    }

    suspend fun setJwtFromCache(){
        this.jwt = "Bearer " + (userPreferencesRepository.getParameter("token").getOrNull()?:"")
    }

}