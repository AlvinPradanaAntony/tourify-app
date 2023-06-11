package com.devcode.tourifyapp.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.devcode.tourifyapp.ui.tabfragment.FeedbackFragment
import com.devcode.tourifyapp.ui.tabfragment.OverviewFragment

class ContentPagerOnDetailAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = OverviewFragment()
            1 -> fragment = FeedbackFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }
}