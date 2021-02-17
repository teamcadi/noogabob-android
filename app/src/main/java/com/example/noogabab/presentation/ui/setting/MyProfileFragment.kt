package com.example.noogabab.presentation.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.noogabab.R
import kotlinx.android.synthetic.main.fragment_my_profile.*
import kotlinx.android.synthetic.main.fragment_setting.*


class MyProfileFragment : Fragment(R.layout.fragment_my_profile) {
    //뒤로 가기 버튼


    //정보 편집 누르면 edit 편집가능해지고 버튼 이름 바뀜 >> 다시 누르면 토스트메세지 띄우기 수정 완료

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_back_setting_my_profile.setOnClickListener{
            clickBackButton()
        }

        btn_setting_my_edit.setOnClickListener{

            if(btn_setting_my_edit.text =="정보 편집") {
                btn_setting_my_edit.text = "수정 완료"
                edit_setting_name.isEnabled = true
                edit_setting_role.isEnabled = true
            }else if(btn_setting_my_edit.text =="수정 완료") {
                btn_setting_my_edit.text ="정보 편집"
                edit_setting_name.isEnabled = false
                edit_setting_role.isEnabled = false
                Toast.makeText(activity,"수정 완료!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun clickBackButton(){
        (activity as SettingsActivity).makeCurrentFragment(SettingFragment())
    }

}