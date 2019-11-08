package com.mvvmsample.example.ui.home.fragment


import androidx.fragment.app.Fragment
import com.mvvmsample.example.R
import com.mvvmsample.example.base.BaseVMFragment
import com.mvvmsample.example.vm.HomeViewModel

/**
 * A simple [Fragment] subclass.
 */
class SquareFragment : BaseVMFragment<HomeViewModel>() {
    override fun providerVMClass(): Class<HomeViewModel>? = HomeViewModel::class.java
    override fun getLayoutResId(): Int =R.layout.fragment_square

    override fun initView() {
    }

    override fun initData() {
    }



}
