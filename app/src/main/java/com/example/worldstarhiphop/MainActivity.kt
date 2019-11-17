package com.example.worldstarhiphop

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.worldstarhiphop.databinding.MainActivityBinding
import androidx.viewpager.widget.ViewPager
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.Window
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.nio.file.Files.size
import androidx.fragment.app.FragmentPagerAdapter
import com.example.worldstarhiphop.ui.main.ArtiestenFragment
import com.example.worldstarhiphop.ui.main.PlaylistsFragment
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        var tabLayout: TabLayout = binding.tabs
        var viewPager: ViewPager = binding.viewpager

        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)

        setupTabIcons()
    }

    private fun setupTabIcons(){
        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_playlist_play_black_24dp)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_person_black_24dp)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(PlaylistsFragment(), "Playlists")
        adapter.addFragment(ArtiestenFragment(), "Artiesten")
        viewPager.adapter = adapter
    }

    // Adapter voor viewpager
    internal class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position)
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList.get(position)
        }
    }


}

