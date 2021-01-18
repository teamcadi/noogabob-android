package com.example.noogabab.ui.start.create

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noogabab.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {
    private val tabTextList = listOf("사용자", "반려동물")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        window.statusBarColor = Color.parseColor("#ffc176")
        loadTab();
    }

    private fun loadTab() {
        val adapter = CreateFragmentAdapter(this)
        adapter.addFragment(UserFragment())
        adapter.addFragment(DogFragment())
        vp_create.adapter = adapter
        TabLayoutMediator(tab_create, vp_create) { t, p -> t.text = tabTextList[p] }.attach()
    }
}