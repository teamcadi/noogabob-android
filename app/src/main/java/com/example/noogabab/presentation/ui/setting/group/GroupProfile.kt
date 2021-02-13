package com.example.noogabab.presentation.ui.setting.group

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noogabab.R
import kotlinx.android.synthetic.main.activity_group_profile.*
import kotlinx.android.synthetic.main.activity_time_line.*

class GroupProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_profile)

        val arrayList = ArrayList<GroupProfileViewModel>()

        arrayList.add(GroupProfileViewModel("유승원","나","10회","10회",R.drawable.ic_profile_group))
        arrayList.add(GroupProfileViewModel("임태호","나","10회","10회",R.drawable.ic_profile_group))
        arrayList.add(GroupProfileViewModel("남태우","나","10회","10회",R.drawable.ic_profile_group))
        arrayList.add(GroupProfileViewModel("박해미","나","10회","10회",R.drawable.ic_profile_group))
        arrayList.add(GroupProfileViewModel("이수정","나","10회","10회",R.drawable.ic_profile_group))

        val myAdapter = GroupProfileAdapter(arrayList,this)


        recyclerview_group_profile.layoutManager = LinearLayoutManager(this)
        recyclerview_group_profile.adapter = myAdapter
    }
}