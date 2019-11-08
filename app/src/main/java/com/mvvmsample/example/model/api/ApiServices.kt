package com.mvvmsample.example.model.api

import com.mvvmsample.example.model.bean.ArticleList
import com.mvvmsample.example.model.bean.Banner
import com.mvvmsample.example.model.bean.User
import com.mvvmsample.example.model.bean.WanResponse
import retrofit2.http.*

interface ApiServices {

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): WanResponse<User>

    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(@Field("username") userName: String, @Field("password") passWord: String, @Field("repassword") rePassWord: String): WanResponse<User>

    @GET("/article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page: Int): WanResponse<ArticleList>


    @GET("/banner/json")
    suspend fun getBanner(): WanResponse<List<Banner>>
}