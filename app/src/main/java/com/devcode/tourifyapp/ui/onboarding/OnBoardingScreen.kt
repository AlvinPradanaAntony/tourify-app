package com.devcode.tourifyapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.databinding.FragmentOnBoardingScreenBinding

class OnBoardingScreen : Fragment() {
    private var _binding: FragmentOnBoardingScreenBinding? = null
    private val binding get() =  _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        if (position == 1) {
            binding.ivImg.setImageResource(R.drawable.img_onboarding1)
            binding.textTitle.text = resources.getString(R.string.headline_on_boarding1)
            binding.textDescription.text = resources.getString(R.string.caption_on_boarding1)
        } else {
            binding.ivImg.setImageResource(R.drawable.img_onboarding2)
            binding.textTitle.text  = resources.getString(R.string.headline_on_boarding2)
            binding.textDescription.text  = resources.getString(R.string.caption_on_boarding2)
        }
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }
}