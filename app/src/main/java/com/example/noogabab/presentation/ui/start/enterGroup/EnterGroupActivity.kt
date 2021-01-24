package com.example.noogabab.presentation.ui.start.enterGroup

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noogabab.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_create.*

class EnterGroupActivity : AppCompatActivity() {
    private val tabTextList = listOf("사용자", "반려동물 정보")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        window.statusBarColor = Color.parseColor("#ffc176")
        loadTab();
    }

    private fun loadTab() {
        val adapter = ViewPagerAdapter(this)
        adapter.addFragment(CreateUserFragment())
        adapter.addFragment(ReadOnlyDogFragment())
        vp_create.adapter = adapter
        TabLayoutMediator(tab_create, vp_create) { t, p -> t.text = tabTextList[p] }.attach()
    }

    fun nextPage() {
        vp_create.setCurrentItem(1, true)
    }
}