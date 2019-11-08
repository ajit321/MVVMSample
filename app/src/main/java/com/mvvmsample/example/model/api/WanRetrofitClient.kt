package com.mvvmsample.example.model.api

import com.mvvmsample.example.app.App
import com.mvvmsample.example.base.BaseRetrofitClient
import com.mvvmsample.example.commn.Constants

import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

object WanRetrofitClient : BaseRetrofitClient() {

    val wServer by lazy { getServer(ApiServices::class.java, Constants.BASE_URL) }

    override fun handleConverterFactory(builder: Retrofit.Builder) {
        builder.addConverterFactory(GsonConverterFactory.create())
    }

    override fun handleBuilder(builder: OkHttpClient.Builder) {
        super.handleBuilder(builder)
        val httpCacheDirectory = File(App.CONTEXT.cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024L // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)
        builder.cache(cache)
            .addInterceptor(CacheInterceptor())
    }
}