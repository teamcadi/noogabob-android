package com.example.noogabab.presentation.ui.main

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.main.album.AlbumFragment
import com.example.noogabab.presentation.ui.main.chart.ChartFragment
import com.example.noogabab.presentation.ui.main.home.HomeFragment
import com.example.noogabab.presentation.ui.main.setting.SettingsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var backPressedTime: Long = 0

    override fun onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
           ActivityCompat.finishAffinity(this);
            exitProcess(0)
        }
        Toast.makeText(this, "한 번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        backPressedTime = System.currentTimeMillis()
    }

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
                R.id.navi_setting -> startActivity(Intent(applicationContext, SettingsActivity::class.java))
            }
            true
        }
    }

    fun changeFragment(@IdRes id: Int) {
        bottom_navi_main.selectedItemId = id
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_main, fragment)
            commit()
        }
}