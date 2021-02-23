package com.example.noogabab.presentation.ui.main.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.main.setting.dog.DogProfileFragment
import com.example.noogabab.presentation.ui.main.setting.group.GroupProfileFragment
import com.example.noogabab.presentation.ui.main.setting.my.MyProfileFragment
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

        linear_group_delete_setting.setOnClickListener{
            //alert Dialog
//            GroupDeleteDialogFragment().show(
//                childFragmentManager, GroupDeleteDialogFragment.TAG)

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