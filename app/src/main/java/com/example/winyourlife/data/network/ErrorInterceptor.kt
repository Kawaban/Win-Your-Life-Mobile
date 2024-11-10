package com.example.winyourlife.data.network

import com.example.winyourlife.data.dto.ErrorResponse
import com.example.winyourlife.data.exceptions.APIException
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.code() in 400..499){
            val errorBody = response.body()?.string()
            val gson = Gson()
            val errorResponse = errorBody?.let {
                gson.fromJson(it, ErrorResponse::class.java)
            }

            throw APIException(errorResponse?.error ?: "Unknown error")
        }

        if(response.code() == 500){
            throw IOException("Internal Server Error");
        }

        return response
    }
}