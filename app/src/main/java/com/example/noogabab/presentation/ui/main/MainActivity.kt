package com.example.noogabab.presentation.ui.main

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.main.album.AlbumFragment
import com.example.noogabab.presentation.ui.main.chart.ChartFragment
import com.example.noogabab.presentation.ui.main.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = Color.parseColor("#ffc176")

        val homeFragment = HomeFragment()
        val chartFragment = ChartFragment()
        val albumFragment = AlbumFragment()
        makeCurrentFragment(homeFragment)
        bottom_navi_main.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navi_home -> makeCurrentFragment(homeFragment)
                R.id.navi_statistics -> makeCurrentFragment(chartFragment)
                R.id.navi_album -> makeCurrentFragment(albumFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_main, fragment)
            commit()
        }
}