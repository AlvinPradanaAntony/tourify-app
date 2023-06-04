package com.devcode.tourifyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.devcode.tourifyapp.ui.onboarding.OnBoardingScreen

class OnBoardingPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        val fragment = OnBoardingScreen()
        fragment.arguments = Bundle().apply {
            putInt(OnBoardingScreen.ARG_SECTION_NUMBER, position + 1)
        }
        return fragment
    }
    override fun getItemCount(): Int {
        return 2
    }
}