package com.example.moviesapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviesapp.databinding.FragmentPopularMoviesBinding
import com.example.moviesapp.ui.fragments.LatestMoviesFragment
import com.example.moviesapp.ui.fragments.PopularMoviesFragment

class ViewPagerAdapter(fragmentManager : FragmentActivity) : FragmentStateAdapter(fragmentManager) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> LatestMoviesFragment()
            1 -> PopularMoviesFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

}