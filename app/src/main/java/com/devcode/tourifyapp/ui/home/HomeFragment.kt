package com.devcode.tourifyapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devcode.tourifyapp.adapter.TravelDataAdapterOffers
import com.devcode.tourifyapp.adapter.TravelDataAdapterRecommendation
import com.devcode.tourifyapp.data.remote.response.TravelDataDummyResponse
import com.devcode.tourifyapp.databinding.FragmentHomeBinding
import com.devcode.tourifyapp.ui.detail.DetailActivity
import com.devcode.tourifyapp.utils.ViewModelFactoryForDummy

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() =  _binding!!
    private lateinit var mainViewModel: HomeViewModel
    private val list = ArrayList<TravelDataDummyResponse>()
    private val adapter: TravelDataAdapterRecommendation by lazy {TravelDataAdapterRecommendation(list) }
    private val adapter2: TravelDataAdapterOffers by lazy {TravelDataAdapterOffers(list)}
    private lateinit var factory: ViewModelFactoryForDummy

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewRecommendation()
        recyclerViewSpecial()
        setupViewModel()
        listDataTravel()
    }

    private fun recyclerViewRecommendation() {
        binding.rvRecommendation.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecommendation.setHasFixedSize(true)
        binding.rvRecommendation.adapter = adapter
        adapter.setOnItemClickCallback(object : TravelDataAdapterRecommendation.OnItemClickCallback {
            override fun onItemClicked(data: TravelDataDummyResponse) {
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                startActivity(intent)
            }
        })
    }

    private fun recyclerViewSpecial() {
        binding.rvSpecialOffers.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSpecialOffers.setHasFixedSize(true)
        binding.rvSpecialOffers.adapter = adapter2
        adapter2.setOnItemClickCallback(object : TravelDataAdapterOffers.OnItemClickCallback {
            override fun onItemClicked(data: TravelDataDummyResponse) {
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                startActivity(intent)
            }
        })
    }

    private fun setupViewModel(){
        factory = ViewModelFactoryForDummy.getInstance(requireActivity())
        mainViewModel = ViewModelProvider(requireActivity() , factory)[HomeViewModel::class.java]
    }
    private fun listDataTravel(){
        val dataList = mainViewModel.getAllData()
        adapter.setData(dataList)
    }
}