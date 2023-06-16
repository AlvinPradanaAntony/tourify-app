package com.devcode.tourifyapp

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.devcode.tourifyapp.adapter.OnBoardingPagerAdapter
import com.devcode.tourifyapp.databinding.ActivityOnboardingBinding
import com.devcode.tourifyapp.ui.login.LoginActivity

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var sectionsPagerAdapter: OnBoardingPagerAdapter
    private lateinit var indicators: Array<TextView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupViewPager()
        setupAction()
    }

    private fun setupView() {
        window?.statusBarColor = ContextCompat.getColor(this@OnBoardingActivity, R.color.white)
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    private fun setupViewPager() {
        sectionsPagerAdapter = OnBoardingPagerAdapter(this)
        viewPager = binding.slideViewPager
        viewPager.adapter = sectionsPagerAdapter
        initializeIndicators(sectionsPagerAdapter.itemCount)
        setUpIndicator(0)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setUpIndicator(position)
                if (position == sectionsPagerAdapter.itemCount - 1) {
                    binding.buttonNavigation.text = getString(R.string.finish)
                } else {
                    binding.buttonNavigation.text = getString(R.string.next)
                }
            }
        })
    }

    private fun setupAction() {
        binding.buttonNavigation.setOnClickListener {
            if (viewPager.currentItem + 1 < sectionsPagerAdapter.itemCount) {
                viewPager.currentItem += 1
            } else {
                startActivity(Intent(this@OnBoardingActivity, LoginActivity::class.java))
            }
        }
        binding.skipButton.setOnClickListener {
            startActivity(Intent(this@OnBoardingActivity, LoginActivity::class.java))
        }
    }

    private fun initializeIndicators(count: Int) {
        indicators = arrayOfNulls(count)
        binding.indicatorLayout.removeAllViews()
        for (i in 0 until count) {
            indicators[i] = TextView(applicationContext)
            indicators[i]?.text = Html.fromHtml("&#8226;")
            indicators[i]?.textSize = 35f
            indicators[i]?.setTextColor(resources.getColor(R.color.grey_400))
            binding.indicatorLayout.addView(indicators[i])
        }
    }
    private fun setUpIndicator(position: Int) {
        if (indicators.isNotEmpty()) {
            for (i in indicators.indices) {
                indicators[i]?.setTextColor(resources.getColor(R.color.grey_400))
            }
            indicators[position]?.setTextColor(resources.getColor(R.color.orange_400))
        }
    }
}
