package com.freenow.app.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.freenow.app.R
import com.freenow.app.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var tabListStrings: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onInitTabTitles()
        onInitAdapter()
        onAttachTabs()
    }

    private fun onInitTabTitles() {
        tabListStrings = arrayListOf(
            resources.getString(R.string.vehicle),
            resources.getString(R.string.map)
        )
    }

    private fun onInitAdapter() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle, tabLayout.tabCount)
        viewPager.adapter = viewPagerAdapter
        viewPager.isUserInputEnabled = false
    }

    private fun onAttachTabs() {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabListStrings[position]
        }.attach()
    }
}
