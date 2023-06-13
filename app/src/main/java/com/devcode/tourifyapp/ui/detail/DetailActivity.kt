package com.devcode.tourifyapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.adapter.ContentPagerOnDetailAdapter
import com.devcode.tourifyapp.data.local.entity.DestinationEntity
import com.devcode.tourifyapp.data.remote.response.DataDestination
import com.devcode.tourifyapp.databinding.ActivityDetailBinding
import com.devcode.tourifyapp.utils.Result
import com.devcode.tourifyapp.utils.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id").toString()

        setupViewModel()
        setupView()
        tabLayout()
        getDataDestination(id)
    }

    private fun getDataDestination(id: String) {
        viewModel.getDetailDestination(id).observe(this) { response ->
            if (response != null) {
                when (response) {
                    Result.Loading -> Log.e("TAG", "getDataDestination: Loading..." )
                    is Result.Success -> {
                        Log.e("TAG", "getDataDestination: ${response.data.data}" )
                        setDataDestination(response.data.data)
                    }
                    is Result.Error -> TODO()
                }
            }
        }
    }

    private fun setDataDestination(data: DataDestination) {
        checkFavorite(data.id, data)
            binding.apply {
                tvPostTitle.text = data.name
            }
    }

    private fun checkFavorite(id: String, data: DataDestination) {
            viewModel.checkFavorite(id)
            viewModel.isExists.observe(this){
                if (it != null) {
                    binding.apply {
                        btnFavourite.apply {
                            setImageResource(R.drawable.ic_favourite)
                            setOnClickListener {
                                viewModel.deleteFavorite(DestinationEntity(data.id, data.name, data.picture))
                                viewModel.checkFavorite(id)
                            }
                        }

                    }
                } else {
                    binding.apply {
                        btnFavourite.apply {
                            setImageResource(R.drawable.ic_favourite_border)
                            setOnClickListener {
                                viewModel.addFavorite(DestinationEntity(data.id, data.name, data.picture))
                                viewModel.checkFavorite(id)
                            }
                        }
                    }
                }
            }
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val viewModels: DetailViewModel by viewModels { factory }
        viewModel = viewModels
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupView() {
        setSupportActionBar(binding.toolbarId)
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