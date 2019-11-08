package com.mvvmsample.example.base

import com.mvvmsample.example.model.bean.Result
import com.mvvmsample.example.model.bean.WanResponse
import java.io.IOException
import java.lang.Exception

open class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> WanResponse<T>): WanResponse<T> {
        return call.invoke()
    }

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Result<T>,
        errorMessage: String
    ): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            Result.Error(IOException(errorMessage, e))
        }
    }
}