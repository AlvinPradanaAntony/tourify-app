package com.devcode.tourifyapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.adapter.CaraouselPagerAdapter
import com.devcode.tourifyapp.adapter.TravelDataOffersAdapter
import com.devcode.tourifyapp.adapter.TravelDataRecommendationsAdapter
import com.devcode.tourifyapp.data.remote.response.TravelDataDummyResponse
import com.devcode.tourifyapp.databinding.FragmentHomeBinding
import com.devcode.tourifyapp.ui.detail.DetailActivity
import com.devcode.tourifyapp.utils.HorizontalMarginItemDecoration
import com.devcode.tourifyapp.utils.ViewModelFactoryForDummy
import com.devcode.tourifyapp.utils.extension.autoScroll
import com.devcode.tourifyapp.utils.extension.scrollToMiddle
import com.devcode.tourifyapp.utils.extension.setCarouselEffects

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: HomeViewModel
    private lateinit var carouselAdapter: CaraouselPagerAdapter
    private lateinit var itemDecoration: HorizontalMarginItemDecoration
    private var listCarousel = mutableListOf<String>()
    private val list = ArrayList<TravelDataDummyResponse>()
    private val adapter: TravelDataRecommendationsAdapter by lazy {
        TravelDataRecommendationsAdapter(
            list
        )
    }
    private val adapter2: TravelDataOffersAdapter by lazy { TravelDataOffersAdapter(list) }
    private lateinit var factory: ViewModelFactoryForDummy

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupCarouselSlider()
        setupData()
        setupAdapter()
        recyclerViewRecommendation()
        recyclerViewSpecial()
        setupViewModel()
        listDataTravel()
    }

    private fun setupCarouselSlider() {
        carouselAdapter = CaraouselPagerAdapter(requireActivity())
        itemDecoration = HorizontalMarginItemDecoration(
            requireActivity(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.viewpagerCarousel.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = carouselAdapter
            scrollToMiddle()
            setCarouselEffects()
            addItemDecoration(itemDecoration)
            autoScroll(lifecycleScope, INTERVAL_TIME)
        }
    }

    private fun setupAdapter() {
        carouselAdapter.setData(listCarousel)
        carouselAdapter.notifyDataSetChanged()
    }

    private fun setupData() {
        listCarousel.add("https://cdn.pixabay.com/photo/2020/12/10/09/22/beach-front-5819728_960_720.jpg")
        listCarousel.add("https://cdn.pixabay.com/photo/2020/09/03/13/56/pine-5541335_960_720.jpg")
        listCarousel.add("https://cdn.pixabay.com/photo/2021/03/04/15/29/river-6068374_960_720.jpg")
        listCarousel.add("https://cdn.pixabay.com/photo/2021/03/29/08/22/peach-flower-6133330_960_720.jpg")
    }

    private fun recyclerViewRecommendation() {
        binding.rvRecommendation.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecommendation.setHasFixedSize(true)
        binding.rvRecommendation.adapter = adapter
        adapter.setOnItemClickCallback(object :
            TravelDataRecommendationsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TravelDataDummyResponse) {
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                startActivity(intent)
            }
        })
    }

    private fun recyclerViewSpecial() {
        binding.rvSpecialOffers.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSpecialOffers.setHasFixedSize(true)
        binding.rvSpecialOffers.adapter = adapter2
        adapter2.setOnItemClickCallback(object : TravelDataOffersAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TravelDataDummyResponse) {
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                startActivity(intent)
            }
        })
    }

    private fun setupViewModel() {
        factory = ViewModelFactoryForDummy.getInstance(requireActivity())
        mainViewModel = ViewModelProvider(requireActivity(), factory)[HomeViewModel::class.java]
    }

    private fun listDataTravel() {
        val dataList = mainViewModel.getAllData()
        adapter.setData(dataList)
    }


    companion object {
        private const val INTERVAL_TIME = 5000L
    }
}