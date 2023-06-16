package com.devcode.tourifyapp.ui.tabfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devcode.tourifyapp.databinding.FragmentOverviewBinding
import com.devcode.tourifyapp.ui.map.MapsActivity


class OverviewFragment : Fragment() {
    private var _binding: FragmentOverviewBinding? = null
    private val binding get() =  _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAction()
    }

    private fun setupAction() {
        binding.btnOnMap.setOnClickListener{
            startActivity(Intent(requireActivity(), MapsActivity::class.java))
        }
        binding.btnFavourite.setOnClickListener{

        }
    }
}