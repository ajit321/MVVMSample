package com.mvvmsample.example.ui.home.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mvvmsample.example.R
import com.mvvmsample.example.base.BaseVMFragment
import com.mvvmsample.example.vm.HomeViewModel

/**
 * A simple [Fragment] subclass.
 */
class ProjectTypeFragment : BaseVMFragment<HomeViewModel>() {
    override fun getLayoutResId(): Int = R.layout.fragment_project_type

    override fun initView() {
    }

    override fun initData() {
    }


    private val cid by lazy { arguments?.getInt(CID) }
    private val isLasted by lazy { arguments?.getBoolean(LASTED) }
    override fun providerVMClass(): Class<HomeViewModel>? = HomeViewModel::class.java


    companion object {
        private const val CID = "projectCid"
        private const val LASTED = "lasted"
        fun newInstance(cid : Int, isLasted: Boolean) : ProjectTypeFragment{
            val mFragment = ProjectTypeFragment()
            val bundle = Bundle()
            bundle.putInt(CID, cid)
            bundle.putBoolean(LASTED, isLasted)
            mFragment.arguments = bundle
            return mFragment
        }
    }


}
