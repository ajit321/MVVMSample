package com.mvvmsample.example.ui.login

import android.app.ProgressDialog
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import com.mvvmsample.example.R
import com.mvvmsample.example.base.BaseVMActivity
import com.mvvmsample.example.commn.launchActivity
import com.mvvmsample.example.ui.home.MainActivity
import com.mvvmsample.example.utils.toast
import com.mvvmsample.example.vm.LoginViewModel

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseVMActivity<LoginViewModel>() {

    override fun providerVMClass(): Class<LoginViewModel>? = LoginViewModel::class.java


    override fun getLayoutResId(): Int = R.layout.activity_login


    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (userNameEt.text.isNullOrBlank() || passwordEt.text.isNullOrBlank()) {
                login.isEnabled = false
                register.isEnabled = false
            } else {
                login.isEnabled = true
                register.isEnabled = true
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }

    override fun initView() {
        login.setOnClickListener { toLogin() }
        register.setOnClickListener { toRegister() }

        userNameEt.addTextChangedListener(textWatcher)
        passwordEt.addTextChangedListener(textWatcher)
    }

    override fun initData() {
    }

    private fun toRegister() {
        val userName = userNameEt.run { text }.run { toString() }.run { trim() }
        val pwd = passwordEt.text.toString().trim()
        mViewModel.register(userName, pwd)
    }

    private fun toLogin() {
        val userName = userNameEt.run { text }.run { toString() }.run { trim() }
        val pwd = passwordEt.text.toString().trim()
        mViewModel.login(userName, pwd)
    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(this@LoginActivity, Observer {
                if (it.showDialog) {
                    showDialog()
                } else {
                    cancelDialog()
                }
                it.apply {
                    if (isLogin) {
                        successData?.let {
                            toast("login successful")
                            launchActivity<MainActivity>()
                            finish()
                        }
                    } else {
                        successData?.let {
                            toast("registration success")
                            launchActivity<MainActivity>()
                            finish()
                        }
                    }
                }
                it.errorMsg?.run {
                    toast(this)
                }
            })
        }

    }

    private var progressDialog : ProgressDialog? = null
    private fun showDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
        }
        progressDialog?.show()
    }
    private fun cancelDialog() {
        progressDialog?.cancel()
    }


}
