package com.example.winyourlife.data.dto

import com.example.winyourlife.data.exceptions.APIError

data class ErrorResponse(
    val error: APIError
)
