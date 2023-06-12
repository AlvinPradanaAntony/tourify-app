package com.devcode.tourifyapp.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginTop
import androidx.viewpager2.widget.ViewPager2
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.adapter.ContentPagerOnDetailAdapter
import com.devcode.tourifyapp.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        tabLayout()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupView() {
        val toolbar = binding.toolbarId
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            val marginTop = resources.getDimensionPixelSize(R.dimen.toolbar_margin_top)
            val toolbarLayoutParams = toolbar.layoutParams as ViewGroup.MarginLayoutParams
            toolbarLayoutParams.topMargin = marginTop
            toolbar.layoutParams = toolbarLayoutParams
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = ""
    }

    private fun tabLayout() {
        val sectionsPagerAdapter = ContentPagerOnDetailAdapter(this)
        val viewPager: ViewPager2 = binding.viewPagerContentOnDetail
        val indikator = binding.indicator
        var indicatorWidth = 0
        viewPager.adapter = sectionsPagerAdapter
        val tabs = binding.tablayoutOnDetail
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        tabs.post {
            indicatorWidth = tabs.width / tabs.tabCount
            val indicatorParams = indikator.layoutParams as FrameLayout.LayoutParams
            indicatorParams.width = indicatorWidth
            indikator.layoutParams = indicatorParams
        }
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int, positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val params = indikator.layoutParams as FrameLayout.LayoutParams
                val translationOffset: Float = (positionOffset + position) * indicatorWidth
                params.leftMargin = translationOffset.toInt()
                indikator.layoutParams = params
            }
        })
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
        )
    }
}