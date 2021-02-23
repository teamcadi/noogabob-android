package com.example.noogabab.presentation.ui.main.setting.my

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.main.setting.SettingFragment
import com.example.noogabab.presentation.ui.main.setting.SettingsActivity
import com.example.noogabab.util.DynamicTextWatcher
import kotlinx.android.synthetic.main.fragment_my_profile.*


class MyProfileFragment : Fragment(R.layout.fragment_my_profile) {
    //뒤로 가기 버튼
    private val viewModel: MyProflieViewModel by activityViewModels()
    private val textWatcher = DynamicTextWatcher(
        onChanged = { _, _, _, _ ->
            viewModel.updateName(edit_setting_name.text.toString())
            viewModel.updateRole(edit_setting_role.text.toString())
        }
    )

    //정보 편집 누르면 edit 편집가능해지고 버튼 이름 바뀜 >> 다시 누르면 토스트메세지 띄우기 수정 완료

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edit_setting_name.addTextChangedListener(textWatcher)
        edit_setting_role.addTextChangedListener(textWatcher)

        btn_back_setting_my_profile.setOnClickListener{
            clickBackButton()
        }

        btn_setting_my_edit.setOnClickListener{

            if(btn_setting_my_edit.text =="정보 편집") {
                btn_setting_my_edit.text = "정보 수정"
                btn_setting_my_edit.isEnabled =false
                edit_setting_name.isEnabled = true
                edit_setting_role.isEnabled = true

            }else if(btn_setting_my_edit.text =="정보 수정") {
                btn_setting_my_edit.text ="정보 편집"
                btn_setting_my_edit.isEnabled =true
                edit_setting_name.isEnabled = false
                edit_setting_role.isEnabled = false
                Toast.makeText(activity,"수정 완료!", Toast.LENGTH_SHORT).show()

            }
            observe()

        }
    }
    private fun observe() {
        viewModel.currentBtnState.observe(requireActivity(), Observer {
            btn_setting_my_edit.isEnabled = it

                if (it) btn_setting_my_edit.setBackgroundColor(requireActivity().getColor(R.color.color_aa5900))
                else btn_setting_my_edit.setBackgroundColor(requireActivity().getColor(R.color.color_e7d0b7))


        })
    }

    private fun clickBackButton(){
        (activity as SettingsActivity).makeCurrentFragment(SettingFragment())
    }

}