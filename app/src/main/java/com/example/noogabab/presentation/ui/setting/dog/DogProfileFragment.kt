package com.example.noogabab.presentation.ui.setting.dog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.setting.SettingFragment
import com.example.noogabab.presentation.ui.setting.SettingsActivity
import com.example.noogabab.presentation.ui.setting.my.MyProflieViewModel
import com.example.noogabab.util.DynamicTextWatcher
import kotlinx.android.synthetic.main.activity_create_group.*
import kotlinx.android.synthetic.main.fragment_dog_profile.*

class DogProfileFragment : Fragment(R.layout.fragment_dog_profile) {
    private val viewModel: DogProfileViewModel by activityViewModels()
    private val textWatcher = DynamicTextWatcher(
        onChanged = { _, _, _, _ ->
            viewModel.updateDogName(edit_settings_dog_name.text.toString())
            viewModel.updateDogAge(edit_settings_dog_age.text.toString())
            viewModel.updateDogKind(edit__settings_dog_kind.text.toString())
        }
    )
    //뒤로 가기 버튼

    //정보 편집 누르면 edit 편집가능해지고 버튼 이름 바뀜 >> 다시 누르면 토스트메세지 띄우기 수정 완료

    //식시 시간은 enable??
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_back_setting_dog_profile.setOnClickListener{
            clickBackButton()
        }

        btn_settings_dog_edit.setOnClickListener {
            if(btn_settings_dog_edit.text =="정보 편집") {
                btn_settings_dog_edit.text = "정보 수정"
                btn_settings_dog_edit.isEnabled = false
                edit_settings_dog_name.isEnabled = true
                edit_settings_dog_age.isEnabled = true
                edit__settings_dog_kind.isEnabled = true

            } else if(btn_settings_dog_edit.text =="정보 수정") {
                btn_settings_dog_edit.text ="정보 편집"
                btn_settings_dog_edit.isEnabled = true
                edit_settings_dog_name.isEnabled = false
                edit_settings_dog_age.isEnabled = false
                edit__settings_dog_kind.isEnabled = false

                Toast.makeText(activity,"수정 완료!",Toast.LENGTH_SHORT).show()
            }
            observe()

        }
    }
    private fun observe() {
        viewModel.currentBtnState.observe(requireActivity(), Observer {
            btn_settings_dog_edit.isEnabled = it
            if (it) btn_settings_dog_edit.setBackgroundColor(requireActivity().getColor(R.color.color_aa5900))
            else btn_settings_dog_edit.setBackgroundColor(requireActivity().getColor(R.color.color_e7d0b7))
        })
    }



    private fun clickBackButton(){
        (activity as SettingsActivity).makeCurrentFragment(SettingFragment())
    }

}
