package com.example.noogabab.presentation.ui.start.createGroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.noogabab.R
import com.example.noogabab.presentation.entity.PresenterBabTime
import kotlinx.android.synthetic.main.activity_create_group.*
import kotlinx.android.synthetic.main.fragment_craete_dog.*

class CreateGroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_group)

        list_view_bab_time.isNestedScrollingEnabled = false
        val adapter = BabTimeListAdapter()
        adapter.addItem(PresenterBabTime("첫 끼", "-", "-"))
        list_view_bab_time.adapter = adapter

        btn_add_bab_time.setOnClickListener {
            Toast.makeText(this, "세끼야", Toast.LENGTH_SHORT).show()
            adapter.addItem(PresenterBabTime("몇 끼", "-", "-"))
            adapter.notifyDataSetChanged()
        }
    }
}