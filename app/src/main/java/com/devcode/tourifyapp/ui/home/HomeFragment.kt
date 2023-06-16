package com.devcode.tourifyapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.devcode.tourifyapp.data.remote.response.RecomItem
import com.devcode.tourifyapp.data.remote.response.TravelDataDummyResponse
import com.devcode.tourifyapp.databinding.FragmentHomeBinding
import com.devcode.tourifyapp.ui.detail.DetailActivity
import com.devcode.tourifyapp.utils.HorizontalMarginItemDecoration
import com.devcode.tourifyapp.utils.Result
import com.devcode.tourifyapp.utils.ViewModelFactory
import com.devcode.tourifyapp.utils.extension.autoScroll
import com.devcode.tourifyapp.utils.extension.setCarouselEffects

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: HomeViewModel
    private lateinit var viewPager: ViewPager2

    private lateinit var itemDecoration: HorizontalMarginItemDecoration
    private val list = ArrayList<TravelDataDummyResponse>()
    private val listRecom = ArrayList<RecomItem>()
    private val carouselList = ArrayList<TravelDataDummyResponse>()
    private val adapter: TravelDataRecommendationsAdapter by lazy { TravelDataRecommendationsAdapter(
        listRecom
    ) }
    private val adapter2: TravelDataOffersAdapter by lazy { TravelDataOffersAdapter(list) }
    private val carouselAdapter: CaraouselPagerAdapter by lazy { CaraouselPagerAdapter(carouselList, viewPager) }
    private lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCarouselSlider()
        recyclerViewRecommendation()
        recyclerViewSpecial()
        setupViewModel()
        listDataTravel()
        listDataCarousel()
    }

    private fun setupCarouselSlider() {
        viewPager = binding.viewpagerCarousel
        itemDecoration = HorizontalMarginItemDecoration(
            requireActivity(),
            R.dimen.viewpager_current_item_horizontal_margin
        )

        viewPager.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = carouselAdapter
            setCarouselEffects()
            addItemDecoration(itemDecoration)
            post{
                val length = carouselAdapter.itemCount
                val middlePosition = length / 2
                Log.d("middlePosition", middlePosition.toString())
                setCurrentItem(middlePosition, true)
            }
            autoScroll(lifecycleScope, INTERVAL_TIME)
        }
    }

    private fun recyclerViewRecommendation() {
        binding.rvRecommendation.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecommendation.setHasFixedSize(true)
        binding.rvRecommendation.adapter = adapter
        adapter.setOnItemClickCallback(object :
            TravelDataRecommendationsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: RecomItem) {
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
        factory = ViewModelFactory.getInstance(requireActivity())
        mainViewModel = ViewModelProvider(requireActivity(), factory)[HomeViewModel::class.java]
    }

    private fun listDataTravel() {
        mainViewModel.getRecomendation().observe(requireActivity()) { data ->
            if (data != null) {
                when (data) {
                    Result.Loading -> Log.e("TAG", "listDataTravel: Loading.." )
                    is Result.Success -> {
                        adapter.setData(data.data.data)
                    }
                    is Result.Error -> TODO()
                }
            }
        }
    }

    private fun listDataCarousel() {
        val dataList = mainViewModel.getAllData()
        carouselAdapter.setData(dataList)
    }

    companion object {
        private const val INTERVAL_TIME = 5000L
    }
}