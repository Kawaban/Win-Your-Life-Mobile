package com.example.winyourlife.data.network

import com.example.winyourlife.data.exceptions.APIException
import com.example.winyourlife.data.exceptions.InternalServerError
import com.example.winyourlife.domain.wrapper.Resource
import com.example.winyourlife.presentation.utils.ExceptionText
import java.io.IOException
import java.net.SocketTimeoutException

suspend fun <T> performNetworkOperation(
    operation: suspend () -> T,
): Resource<T> {
    return try {
        Resource.Success(operation())
    } catch (e: SocketTimeoutException) {
        println(e.printStackTrace())
        Resource.Error(ExceptionText.ServerIsDown.text, null)
    } catch (e: APIException) {
        println(e.printStackTrace())
        Resource.Error(e.message ?: ExceptionText.Unknown.text, null)
    } catch (e: InternalServerError){
        println(e.printStackTrace())
        println("xdd")
        Resource.Error(ExceptionText.InternalServer.text, null)
    }
    catch (e: IOException) {
        println(e.printStackTrace())
        println("wpp")
        Resource.Error(ExceptionText.Net.text, null)
    } catch (e: Exception) {
        println(e.printStackTrace())
        println("ahahah")
        Resource.Error(ExceptionText.Unknown.text, null)
    }
}