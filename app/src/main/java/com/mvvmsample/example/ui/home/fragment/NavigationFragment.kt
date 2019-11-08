package com.mvvmsample.example.ui.home.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mvvmsample.example.R
import com.mvvmsample.example.base.BaseVMFragment
import com.mvvmsample.example.vm.HomeViewModel

/**
 * A simple [Fragment] subclass.
 */
class NavigationFragment :  BaseVMFragment<HomeViewModel>() {
    override fun providerVMClass(): Class<HomeViewModel>? = HomeViewModel::class.java


    override fun getLayoutResId(): Int =R.layout.fragment_navigation

    override fun initView() {
    }

    override fun initData() {
    }




}
