package com.devcode.tourifyapp.ui.explore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.devcode.tourifyapp.adapter.TravelPlaceAdapter
import com.devcode.tourifyapp.adapter.TravelPlaceSearchAdapter
import com.devcode.tourifyapp.databinding.FragmentExploreBinding
import com.devcode.tourifyapp.utils.Result
import com.devcode.tourifyapp.utils.ViewModelFactory


class ExploreFragment : Fragment() {
    private var _binding: FragmentExploreBinding? = null
    private val binding get() =  _binding!!
    private lateinit var viewModel: ExploreViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        getDestination()
        rvSetting()
        searchSetting()
    }

    private fun searchSetting() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchDestination(query)
                binding.search.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    getDestination()
                }
                return true
            }
        })
    }

    private fun getDestination() {
        viewModel.getAllDestination().observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when (response) {
                    Result.Loading -> Log.e("Loading", "getDestination: Loading..." )
                    is Result.Success -> {
                        Log.e("TAG", "getDestination: ${response.data}")
                        val adapter = TravelPlaceAdapter(response.data.data)
                        binding.rvList.adapter = adapter
                    }
                    is Result.Error -> Log.e("Error", "getDestination: ${response.error}" )
                }
            }
        }
    }

    private fun searchDestination(name: String) {
        viewModel.searchDestination(name).observe(requireActivity()) { response ->
            if (response != null) {
                when (response) {
                    Result.Loading -> Log.e("Loading", "getDestination: Loading..." )
                    is Result.Success -> {
                        Log.e("TAG", "getDestination: ${response.data}")
                        val adapter = TravelPlaceSearchAdapter(response.data.data)
                        binding.rvList.adapter = adapter
                    }
                    is Result.Error -> TODO()
                }
            }
        }
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        val viewModels: ExploreViewModel by viewModels { factory }
        viewModel = viewModels
    }

    private fun rvSetting() {

        binding.apply {
            rvList.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            rvList.setHasFixedSize(true)
        }
    }


    companion object {

    }
}