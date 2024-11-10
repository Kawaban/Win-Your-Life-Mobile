package com.example.winyourlife.data.network

import com.example.winyourlife.data.exceptions.APIException
import com.example.winyourlife.domain.wrapper.Resource
import java.io.IOException
import java.net.SocketTimeoutException

suspend fun <T> performNetworkOperation(
    operation: suspend () -> T,
): Resource<T> {
    return try {
        Resource.Success(operation())
    } catch (e: SocketTimeoutException) {
        println(e.printStackTrace())
        Resource.Error("Server is not available", null)
    } catch (e: APIException) {
        println(e.printStackTrace())
        Resource.Error(e.message ?: "Unknown error", null)
    } catch (e: IOException) {
        println(e.printStackTrace())
        Resource.Error("Network error", null)
    } catch (e: Exception) {
        println(e.printStackTrace())
        Resource.Error("Unknown application error", null)
    }
}