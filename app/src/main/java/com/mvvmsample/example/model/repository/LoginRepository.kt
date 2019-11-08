package com.mvvmsample.example.model.repository

import com.mvvmsample.example.base.BaseRepository
import com.mvvmsample.example.model.api.WanRetrofitClient
import com.mvvmsample.example.model.bean.Result
import com.mvvmsample.example.model.bean.User
import java.io.IOException

class LoginRepository : BaseRepository() {

    suspend fun login(userName : String, pwd : String) : Result<User> {
        return safeApiCall({requestLogin(userName, pwd)}, "Login failed")
    }

    private suspend fun requestLogin(userName : String, pwd : String) : Result<User> {
        val response = WanRetrofitClient.wServer.login(userName, pwd)
        return if (response.errorCode != -1) {
            Result.Success(response.data)
        } else {
            Result.Error(IOException(response.errorMsg))
        }
    }

    suspend fun register(userName: String, pwd: String) : Result<User> {
        return safeApiCall({requestRegister(userName, pwd)}, "registration failed")
    }

    private suspend fun requestRegister(userName: String, pwd: String) : Result<User> {
        val response = WanRetrofitClient.wServer.register(userName, pwd, pwd)
        return if (response.errorCode != -1) Result.Success(response.data) else Result.Error(IOException(response.errorMsg))
    }

}