package com.devcode.tourifyapp.ui.tabfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devcode.tourifyapp.adapter.FeedBackAdapter
import com.devcode.tourifyapp.adapter.TravelDataRecommendationsAdapter
import com.devcode.tourifyapp.data.remote.response.ReviewsResponse
import com.devcode.tourifyapp.data.remote.response.TravelDataDummyResponse
import com.devcode.tourifyapp.databinding.FragmentFeedbackBinding
import com.devcode.tourifyapp.ui.detail.DetailActivity
import com.devcode.tourifyapp.ui.home.HomeViewModel
import com.devcode.tourifyapp.utils.ViewModelFactoryForDummy


class FeedbackFragment : Fragment() {
    private var _binding: FragmentFeedbackBinding? = null
    private val binding get() =  _binding!!
    private lateinit var feedbackViewModel: FeedbackViewModel
    private val list = ArrayList<ReviewsResponse>()
    private lateinit var factory: ViewModelFactoryForDummy
    private val adapter: FeedBackAdapter by lazy { FeedBackAdapter(list) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewReviews()
        setupViewModel()
        listDataReviews()
    }

    private fun setupViewModel() {
        factory = ViewModelFactoryForDummy.getInstance(requireActivity())
        feedbackViewModel = ViewModelProvider(requireActivity(), factory)[FeedbackViewModel::class.java]
    }

    private fun recyclerViewReviews() {
        binding.recyclerViewReviews.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewReviews.setHasFixedSize(true)
        binding.recyclerViewReviews.adapter = adapter
    }

    private fun listDataReviews() {
        val dataList = feedbackViewModel.getAllData()
        adapter.setData(dataList)
    }
}