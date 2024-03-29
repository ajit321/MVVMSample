package com.mvvmsample.example.ui.home.fragment


import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvmsample.example.R
import com.mvvmsample.example.base.BaseVMFragment
import com.mvvmsample.example.commn.onNetError
import com.mvvmsample.example.model.bean.ArticleList
import com.mvvmsample.example.model.bean.Banner
import com.mvvmsample.example.ui.home.adapter.HomeArticleAdapter
import com.mvvmsample.example.ui.home.adapter.HomeBannerAdapter
import com.mvvmsample.example.utils.toast
import com.mvvmsample.example.vm.HomeViewModel

import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_header_view.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment :  BaseVMFragment<HomeViewModel>() {
    val TAG = "HomeFragment"
    private var pagerIndex = 0
    private val mLayoutManager by lazy { LinearLayoutManager(context) }
    private val mArticleAdapter by lazy { HomeArticleAdapter() }
    private val mHeaderView by lazy { View.inflate(context, R.layout.home_header_view, null) }
    private val mBannerAdapter by lazy { HomeBannerAdapter(context!!) }
    override fun providerVMClass(): Class<HomeViewModel>? = HomeViewModel::class.java
    override fun getLayoutResId(): Int = R.layout.fragment_home

    override fun initView() {
        initBanner()
        initAdapter()
        mRecyclerView.run {
            layoutManager = mLayoutManager
            adapter = mArticleAdapter
        }
        homeRefreshLayout.run {
            isRefreshing = true
            setOnRefreshListener { refresh() }
        }
        refresh()
    }

    override fun initData() {
    }
    private fun initBanner() {
        mHeaderView.viewPager.run {
            adapter = mBannerAdapter
        }
    }

    private fun initAdapter() {
        mArticleAdapter.run {
            setOnItemClickListener { _, _, position -> context?.toast(position.toString())}
            setOnLoadMoreListener({loadMore()}, mRecyclerView)
            addHeaderView(mHeaderView)
        }
    }

    private fun loadMore() {
        mArticleAdapter.isLoadMoreEnable
        pagerIndex ++
        getData(pagerIndex)
    }

    private fun refresh() {
        getData(0)
    }

    private fun getData(pagerIndex : Int) {
        mViewModel.getHomeArticles(pagerIndex) {
            context?.toast("error")
            homeRefreshLayout.isRefreshing = false
            mArticleAdapter.loadMoreComplete()
        }
    }

    override fun startObserve() {
       // super.startObserve()
        mViewModel.apply {
            articleData.observe(this@HomeFragment, Observer { articleList -> updateArticleData(articleList) })
            mBanner.observe(this@HomeFragment, Observer { banner -> updateBannerData(banner) })
        }
    }
    private fun updateArticleData(articleList: ArticleList) {
        mArticleAdapter.run {
            if (homeRefreshLayout.isRefreshing) {
                replaceData(articleList.datas)
                homeRefreshLayout.isRefreshing = false
            } else {
                addData(articleList.datas)
                loadMoreComplete()
            }
        }
    }

    override fun onError(e: Throwable) {
        super.onError(e)
//        Log.d(TAG, "onError = " + e.message)
//        context?.toast("没有网络")
//        homeRefreshLayout.isRefreshing = false
//        mArticleAdapter.loadMoreComplete()

        activity?.onNetError(e){
            Log.d(TAG, "onError = " + e.message)
            homeRefreshLayout.isRefreshing = false
            mArticleAdapter.loadMoreComplete()
        }
    }

    private fun updateBannerData(banner: List<Banner>) {
        mBannerAdapter.setData(banner)
    }


}
