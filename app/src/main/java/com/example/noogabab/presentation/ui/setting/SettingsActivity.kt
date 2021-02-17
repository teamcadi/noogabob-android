package com.example.noogabab.presentation.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.noogabab.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val firstFragment = SettingFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout_settings, firstFragment).commit()
    }


    public fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout_settings, fragment)
            commit()
        }
}