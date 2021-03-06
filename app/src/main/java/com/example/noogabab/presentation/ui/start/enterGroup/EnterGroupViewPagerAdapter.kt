package com.example.noogabab.presentation.ui.start.enterGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class EnterGroupViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    private val fragments = mutableListOf<Fragment>()

    override fun getItemCount() = fragments.size
    override fun createFragment(position: Int) = fragments[position]
    public fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
        notifyItemInserted(fragments.size - 1)
    }
}