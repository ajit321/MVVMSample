package com.mvvmsample.example.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mvvmsample.example.R


abstract class BaseVMActivity<VM : BaseViewModel> : AppCompatActivity(), LifecycleObserver {

    lateinit var mViewModel : VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVM()
        startObserve()
        setContentView(getLayoutResId())
        initView()
        initData()
    }

    private fun initVM() {
        providerVMClass()?.let {
            mViewModel = ViewModelProvider(this).get(it)
            mViewModel.let {
                lifecycle::addObserver
            }
        }
    }
    protected fun setToolbarTitle(title: String) {
        val mToolbar = findViewById<Toolbar>(R.id.toolbar)
        mToolbar.title = title
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorAccent))
        mToolbar.setNavigationIcon(R.drawable.ic_chevron_left_24_px)
        setSupportActionBar(mToolbar)
    }
    open fun providerVMClass() : Class<VM>? = null

    open fun startObserve() {
        mViewModel.mExceptions.observe(this, Observer { t -> onError(t) })
    }

    open fun onError(e: Throwable) {}

    abstract fun getLayoutResId(): Int
    abstract fun initView()
    abstract fun initData()

    override fun onDestroy() {
        lifecycle.removeObserver(mViewModel)
        super.onDestroy()
    }

}