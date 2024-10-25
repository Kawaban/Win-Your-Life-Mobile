package com.example.winyourlife.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JwtManager @Inject constructor() {
    private var jwt: String? = null

    fun setJwt(jwt: String) {
        this.jwt = "Bearer $jwt"
    }

    fun getJwt(): String? {
        return jwt
    }

    fun isJwtSet(): Boolean {
        return jwt != null
    }
}