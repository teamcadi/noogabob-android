package com.example.noogabab.presentation.ui.main.setting

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.noogabab.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val firstFragment = SettingFragment()
        window.statusBarColor = Color.parseColor("#ffb254")

        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout_settings, firstFragment).commit()
    }


    fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout_settings, fragment)
            commit()
        }
}