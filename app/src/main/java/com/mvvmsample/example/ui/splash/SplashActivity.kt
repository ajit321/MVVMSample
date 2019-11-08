package com.mvvmsample.example.ui.splash

import android.os.Handler
import com.mvvmsample.example.R
import com.mvvmsample.example.base.BaseActivity
import com.mvvmsample.example.commn.launchActivity
import com.mvvmsample.example.ui.login.LoginActivity


class SplashActivity : BaseActivity() {
    companion object {
        const val deleySeconds = 3000
    }

    override fun initView() {
        Handler().let { handlername ->
            handlername.postDelayed({
                launchActivity<LoginActivity>()
                finish()
            }, deleySeconds.toLong())
        }
    }

    override fun initData() {

    }

    override fun getLayoutResId(): Int = R.layout.activity_splash


}
