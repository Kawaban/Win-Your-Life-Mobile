package com.example.winyourlife.data.network

import com.example.winyourlife.data.dto.ErrorResponse
import com.example.winyourlife.data.exceptions.APIException
import com.example.winyourlife.data.exceptions.InternalServerError
import com.example.winyourlife.presentation.utils.ExceptionText
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
            println(errorResponse)

            throw APIException(mapApiException(errorResponse?.error?.message))
        }

        if(response.code() == 500){
            throw InternalServerError("Internal Server Error")
        }

        return response
    }

    private fun mapApiException(error:String?):String{
        return when(error){
            "Wrong password or email" -> {
                ExceptionText.BadCredentials.text
            }
            "Email already used" -> {
                ExceptionText.UserAlreadyExists.text
            }
            "Entity not found" -> {
                ExceptionText.BadInput.text
            }
            "Invalid input" -> {
                ExceptionText.BadInput.text
            }
            else -> {
                ExceptionText.Unknown.text
            }
        }
    }


}