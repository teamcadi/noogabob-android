package com.example.noogabab.presentation.ui.setting

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.main.MainActivity
import com.example.noogabab.presentation.ui.main.chart.ChartFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment(R.layout.fragment_setting) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        switch_setting.setOnClickListener{
            //푸쉬알림 switch 눌렀을때
        }

        linear_my_profile_setting.setOnClickListener{
            clickMyProfile()
        }
        linear_goup_profile_setting.setOnClickListener{
            clickGroupProfile()
        }
        linear_dog_profile_setting.setOnClickListener{
            clickDogProfile()
        }

        iv_group_delete_setting.setOnClickListener{
            //alert Dialog
        }

    }


    private fun clickMyProfile(){
        (activity as SettingsActivity).makeCurrentFragment(MyProfileFragment())
    }
    private fun clickGroupProfile(){
        (activity as SettingsActivity).makeCurrentFragment(GroupProfileFragment())
    }
    private fun clickDogProfile(){
        (activity as SettingsActivity).makeCurrentFragment(DogProfileFragment())
    }

}