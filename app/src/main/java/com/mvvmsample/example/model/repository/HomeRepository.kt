package com.mvvmsample.example.model.repository

import com.mvvmsample.example.base.BaseRepository
import com.mvvmsample.example.model.api.WanRetrofitClient
import com.mvvmsample.example.model.bean.ArticleList
import com.mvvmsample.example.model.bean.Banner
import com.mvvmsample.example.model.bean.WanResponse

class HomeRepository: BaseRepository()  {

    suspend fun getHomeArticles(pageIndex : Int): WanResponse<ArticleList> {
        return apiCall { WanRetrofitClient.wServer.getHomeArticles(pageIndex)}
    }

    suspend fun getHomeBanners() : WanResponse<List<Banner>> {
        return apiCall { WanRetrofitClient.wServer.getBanner() }
    }
}