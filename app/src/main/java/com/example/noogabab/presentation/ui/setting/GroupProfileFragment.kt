package com.example.noogabab.presentation.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.setting.group.GroupProfileAdapter
import com.example.noogabab.presentation.ui.setting.group.GroupProfileViewModel
import kotlinx.android.synthetic.main.fragment_group_profile.*
import kotlinx.android.synthetic.main.fragment_my_profile.*


class GroupProfileFragment : Fragment(R.layout.fragment_group_profile) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_back_setting_group_profile.setOnClickListener{
            clickBackButton()
        }

        val arrayList = ArrayList<GroupProfileViewModel>()

        arrayList.add(GroupProfileViewModel("유승원","나","10회","10회",R.drawable.ic_profile_group))
        arrayList.add(GroupProfileViewModel("임태호","나","10회","10회",R.drawable.ic_profile_group))
        arrayList.add(GroupProfileViewModel("남태우","나","10회","10회",R.drawable.ic_profile_group))
        arrayList.add(GroupProfileViewModel("박해미","나","10회","10회",R.drawable.ic_profile_group))
        arrayList.add(GroupProfileViewModel("이수정","나","10회","10회",R.drawable.ic_profile_group))

        val myAdapter = GroupProfileAdapter(arrayList)


        recyclerview_settings_group_profile.layoutManager = LinearLayoutManager(activity)
        recyclerview_settings_group_profile.adapter = myAdapter


    }


    private fun clickBackButton(){
        (activity as SettingsActivity).makeCurrentFragment(SettingFragment())
    }
}