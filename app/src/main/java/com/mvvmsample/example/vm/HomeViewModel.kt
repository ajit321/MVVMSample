package com.mvvmsample.example.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.mvvmsample.example.base.BaseViewModel
import com.mvvmsample.example.commn.executeResponse
import com.mvvmsample.example.model.bean.ArticleList
import com.mvvmsample.example.model.bean.Banner
import com.mvvmsample.example.model.repository.HomeRepository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel: BaseViewModel() {
    private val homeRepository by lazy { HomeRepository() }
    val articleData : MutableLiveData<ArticleList> = MutableLiveData()

    val mBanner : LiveData<List<Banner>> = liveData {
        kotlin.runCatching {
            val data = withContext(Dispatchers.IO) { homeRepository.getHomeBanners() }
            emit(data.data)
        }
    }

    fun getHomeArticles(pageIndex : Int, errorBlock: suspend CoroutineScope.()-> Unit) {
        launch {
            val result = withContext(Dispatchers.IO) {
                homeRepository.getHomeArticles(pageIndex)
            }
            executeResponse(result, {articleData.value = result.data}, errorBlock)
        }
    }
}