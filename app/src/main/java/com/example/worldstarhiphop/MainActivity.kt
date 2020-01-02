package com.example.worldstarhiphop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.worldstarhiphop.databinding.MainActivityBinding
import androidx.viewpager.widget.ViewPager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.worldstarhiphop.artists.ArtistFragment
import com.example.worldstarhiphop.favorites.FavoritesFragment
import com.example.worldstarhiphop.radios.RadioFragment
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        val tabLayout: TabLayout = binding.tabs
        val viewPager: ViewPager = binding.viewpager

        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)

        setupTabIcons()
    }

    private fun setupTabIcons(){
        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_playlist_play_black_24dp)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_favorite_border_black_24dp)
        binding.tabs.getTabAt(2)!!.setIcon(R.drawable.ic_radio_black_24dp)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ArtistFragment(), "Artiesten")
        adapter.addFragment(FavoritesFragment(), "Favorieten")
        adapter.addFragment(RadioFragment(), "Radio")
        viewPager.adapter = adapter
    }

    // Adapter voor viewpager
    internal class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }


}

