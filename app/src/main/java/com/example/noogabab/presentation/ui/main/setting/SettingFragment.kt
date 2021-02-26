package com.example.noogabab.presentation.ui.main.setting

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.example.noogabab.R
import com.example.noogabab.presentation.dialog.AlertDialog
import com.example.noogabab.presentation.ui.main.setting.dog.DogProfileFragment
import com.example.noogabab.presentation.ui.main.setting.group.GroupProfileFragment
import com.example.noogabab.presentation.ui.main.setting.my.MyProfileFragment
import com.example.noogabab.util.SharedDog
import com.example.noogabab.util.SharedGroup
import kotlinx.android.synthetic.main.dialog_create_group.*
import kotlinx.android.synthetic.main.fragment_enter_main.*
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
        linear_group_key_setting.setOnClickListener {
            val sharedGroup = requireActivity().getSharedPreferences(SharedGroup.NAME, Context.MODE_PRIVATE)
            txt_setting_group_key.setText(sharedGroup.getString(SharedGroup.GROUP_UUID_KEY, ""))
            val text = txt_setting_group_key.text

            val clipboardManager: ClipboardManager = requireActivity().getSystemService(Activity.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData: ClipData = ClipData.newPlainText("create_key", text.toString())
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(requireContext(), "$text 복사 완료", Toast.LENGTH_SHORT).show()

        }

        linear_group_delete_setting.setOnClickListener{
            AlertDialog().defaultAlert(
                requireContext(), "그룹을 탈퇴하시겠습니까?", "탈퇴시 복구가 불가능합니다.", "확인", "취소", {
                }, {
                })
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