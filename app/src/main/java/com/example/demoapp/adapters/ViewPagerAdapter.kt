package com.example.demoapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.demoapp.view.frgments.ApplicationFragment
import com.example.demoapp.view.frgments.SettingsFragment

private const val NUM_PAGES = 2
class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager,lifecycle)
{
    override fun getItemCount(): Int {
      return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> ApplicationFragment()
            1-> SettingsFragment()
            else -> ApplicationFragment()
        }
    }
}