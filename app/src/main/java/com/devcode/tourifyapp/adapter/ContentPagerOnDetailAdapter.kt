package com.devcode.tourifyapp.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.devcode.tourifyapp.ui.detail.tabfragment.FeedbackFragment
import com.devcode.tourifyapp.ui.detail.tabfragment.OverviewFragment

class ContentPagerOnDetailAdapter(activity: AppCompatActivity, private val id: String) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        val bundle = Bundle()
        when (position) {
            0 -> fragment = OverviewFragment()
            1 -> fragment = FeedbackFragment()
        }
        bundle.putString(ID, id)
        fragment?.arguments = bundle
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }

    companion object {
        const val ID = "id"
    }
}