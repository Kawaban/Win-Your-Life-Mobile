package com.example.winyourlife.data.network

import com.example.winyourlife.data.dto.ErrorResponse
import com.example.winyourlife.data.exceptions.BadCredentialsException
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.code() == 400) {
            val errorBody = response.body()?.string()
            val gson = Gson()
            val errorResponse = errorBody?.let {
                gson.fromJson(it, ErrorResponse::class.java)
            }

            throw BadCredentialsException(errorResponse?.error ?: "Unknown error")
        }

        if (response.code() == 404) {
            val errorBody = response.body()?.string()
            val gson = Gson()
            val errorResponse = errorBody?.let {
                gson.fromJson(it, ErrorResponse::class.java)
            }

            throw BadCredentialsException(errorResponse?.error ?: "Unknown error")
        }

        if(response.code() == 500){
            throw IOException("Internal Server Error");
        }

        return response
    }
}