package com.devcode.tourifyapp.ui.detail.tabfragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.data.local.entity.DestinationEntity
import com.devcode.tourifyapp.data.remote.response.DataDestination
import com.devcode.tourifyapp.databinding.FragmentOverviewBinding
import com.devcode.tourifyapp.ui.detail.DetailViewModel
import com.devcode.tourifyapp.ui.map.MapsActivity
import com.devcode.tourifyapp.utils.Result
import com.devcode.tourifyapp.utils.ViewModelFactory


class OverviewFragment : Fragment() {
    private var _binding: FragmentOverviewBinding? = null
    private val binding get() =  _binding!!
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString(ID)
        setupViewModel()
        viewModel.checkFavorite(id.toString())
        getDataDestination(id)
    }

    private fun getDataDestination(id: String?) {
        viewModel.getDetailDestination(id.toString()).observe(requireActivity()) { response ->
            if (response != null) {
                when (response) {
                    Result.Loading -> Log.e("TAG", "getDataDestination: Loading..." )
                    is Result.Success -> {
                        setDataDestination(response.data.data)
                    }
                    is Result.Error -> Log.e("TAG", "getDataDestination: ${response.error}" )
                }
            }
        }
    }

    private fun setDataDestination(data: DataDestination) {
        checkFavorite(data)
        binding.apply {
            tvDescription.text = data.description
            tvCategory.text = data.tourCategory.name
            tvPrice.text = data.price
            tvAddress.text = data.address
            btnOnMap.setOnClickListener{
                val intent = Intent(requireActivity(), MapsActivity::class.java)
                intent.putExtra(EXTRA_LATITUDE, data.latitude)
                intent.putExtra(EXTRA_LONGITUDE, data.longitude)
                startActivity(intent)
            }
        }
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModels: DetailViewModel by viewModels { factory }
        viewModel = viewModels
    }

    private fun checkFavorite(data: DataDestination) {
            viewModel.isExists.observe(requireActivity()){
                if (it != null) {
                    binding.apply {
                        btnFavourite.apply {
                            setImageResource(R.drawable.ic_favourite)
                            setOnClickListener {
                                viewModel.deleteFavorite(DestinationEntity(data.id, data.name, data.address, data.picture))
                                setImageResource(R.drawable.ic_favourite_border)
                            }
                        }

                    }
                } else {
                    binding.apply {
                        btnFavourite.apply {
                            setImageResource(R.drawable.ic_favourite_border)
                            setOnClickListener {
                                viewModel.addFavorite(DestinationEntity(data.id, data.name, data.address, data.picture))
                                setImageResource(R.drawable.ic_favourite)
                            }
                        }
                    }
                }
            }
    }

    companion object {
        private const val ID = "id"
        private const val EXTRA_LATITUDE = "LAT"
        private const val EXTRA_LONGITUDE = "LONG"

    }
}