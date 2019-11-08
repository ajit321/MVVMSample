package com.mvvmsample.example.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.mvvmsample.example.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel


abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView()
        initData()
    }
    protected fun setToolbarTitle(title: String) {
        val mToolbar = findViewById<Toolbar>(R.id.toolbar)
        mToolbar.title = title
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorAccent))
        mToolbar.setNavigationIcon(R.drawable.ic_chevron_left_24_px)
        setSupportActionBar(mToolbar)
    }
    abstract fun initView()

    abstract fun initData()

    abstract fun getLayoutResId(): Int

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}