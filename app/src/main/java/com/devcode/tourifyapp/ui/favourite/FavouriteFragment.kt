package com.devcode.tourifyapp.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.devcode.tourifyapp.adapter.FavoritePlaceAdapter
import com.devcode.tourifyapp.databinding.FragmentFavouriteBinding
import com.devcode.tourifyapp.utils.ViewModelFactory

class FavouriteFragment : Fragment() {
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() =  _binding!!
    private lateinit var adapter: FavoritePlaceAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvSetting()
        setupViewModel()
        getData()
    }

    private fun getData() {
        viewModel.listFavorite.observe(requireActivity()) { data ->
            adapter.setData(data)
        }
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModels: FavoriteViewModel by viewModels { factory }
        viewModel = viewModels
    }

    private fun rvSetting() {
        adapter = FavoritePlaceAdapter(mutableListOf())

        binding.apply {
            rvList.setHasFixedSize(true)
            rvList.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            rvList.adapter = adapter
        }
    }
}