package com.mvvmsample.example.ui.home

import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.navigation.NavigationView
import com.mvvmsample.example.R
import com.mvvmsample.example.base.BaseActivity
import com.mvvmsample.example.commn.launchActivity
import com.mvvmsample.example.ui.home.fragment.*
import com.mvvmsample.example.ui.login.LoginActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener{

    private val tabList = arrayOf("Home", "Plaza", "Latest Project", "System", "Navigation")
    private val fragmentList = arrayListOf<Fragment>()

    private val homeFragment by lazy { HomeFragment() }
    private val squareFragment by lazy { SquareFragment() }
    private val projectTypeFragment by lazy { ProjectTypeFragment.newInstance(0, true) }
    private val systemFragment by lazy { SystemFragment() }
    private val navigationFragment by lazy { NavigationFragment() }
    private val fragmentManager by lazy { this.supportFragmentManager }

    private val mViewPagerAdapter = object : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment = fragmentList[position]

        override fun getCount(): Int = fragmentList.size

        override fun getPageTitle(position: Int): CharSequence? {
            return tabList[position]
        }
    }
    init {
        fragmentList.add(homeFragment)
        fragmentList.add(squareFragment)
        fragmentList.add(projectTypeFragment)
        fragmentList.add(systemFragment)
        fragmentList.add(navigationFragment)
    }

    override fun initView() {
        navigationView.setNavigationItemSelectedListener(this)
        initViewPager()
        mainToolBar.setNavigationOnClickListener { drawer.openDrawer(GravityCompat.START) }
    }

    private fun initViewPager() {
        viewPager.run {
            offscreenPageLimit = fragmentList.size
            adapter = mViewPagerAdapter
        }
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun initData() {
    }

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_login -> { launchActivity<LoginActivity>()}
            else -> {}
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


}
