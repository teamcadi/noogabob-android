package com.example.noogabab.presentation.ui.main.setting.dog

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.noogabab.R
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.presentation.ui.main.MainActivity
import com.example.noogabab.presentation.ui.main.setting.SettingFragment
import com.example.noogabab.presentation.ui.main.setting.SettingsActivity
import com.example.noogabab.presentation.ui.start.BobTimeView
import com.example.noogabab.presentation.ui.start.enterGroup.EnterGroupActivity
import com.example.noogabab.util.DynamicTextWatcher
import com.example.noogabab.util.SharedDog
import com.example.noogabab.util.SharedGroup
import com.example.noogabab.util.SharedUser
import kotlinx.android.synthetic.main.fragment_dog_profile.*
import kotlinx.android.synthetic.main.fragment_dog_profile.btn_back_setting_dog_profile
import kotlinx.android.synthetic.main.fragment_dog_profile.btn_settings_dog_edit
import kotlinx.android.synthetic.main.fragment_enter_main.*
import kotlinx.android.synthetic.main.fragment_my_profile.*

class DogProfileFragment : Fragment(R.layout.fragment_dog_profile) {
    private lateinit var sharedDog: SharedPreferences
    private val viewModel: DogProfileViewModel by activityViewModels<DogProfileViewModel>()
    private val textWatcher = DynamicTextWatcher(
        onChanged = { _, _, _, _ ->
            viewModel.updateDogName(edit_settings_dog_name.text.toString())
            viewModel.updateDogAge(edit_settings_dog_age.text.toString())
            viewModel.updateDogKind(edit__settings_dog_kind.text.toString())
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedDog = requireActivity().getSharedPreferences(SharedDog.NAME, Context.MODE_PRIVATE)
        viewModel.updateDogName(sharedDog.getString(SharedDog.DOG_NAME_KEY, "")!!)
        viewModel.updateDogAge(sharedDog.getInt(SharedDog.DOG_AGE_KEY, -1)!!.toString())
        viewModel.updateDogKind(sharedDog.getString(SharedDog.DOG_KIND_KEY, "")!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()

        edit_settings_dog_name.addTextChangedListener(textWatcher)
        edit_settings_dog_age.addTextChangedListener(textWatcher)
        edit__settings_dog_kind.addTextChangedListener(textWatcher)

        val meals = sharedDog.getString(SharedDog.DOG_MEALS_KEY, "")!!.split(",")
        val countBob = arrayOf("첫 끼", "두 끼", "세 끼")
        for (i in meals.indices) {
            var bobTime =
                BobTimeView(context = requireContext(), supportFM = null, viewModel = null)
            val timeList = meals[i].split(":")
            bobTime.setBobTime(
                countBob[i],
                if (timeList[0].toInt() <= 12) "오전" else "오후",
                if (timeList[0].toInt() <= 12) "${timeList[0]}:${timeList[1]}" else "${timeList[0].toInt() - 12}:${timeList[1]}"
            )
            linear_settings_bob_time.addView(bobTime)
        }



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

        }
    }
    private fun observe() {
        viewModel.currentDogKind.observe(requireActivity(), Observer {
            edit_settings_dog_name.setText(viewModel.getDogName!!)
            edit_settings_dog_age.setText(viewModel.getDogAge!!)
            edit__settings_dog_kind.setText(it)
        })
        viewModel.currentBtnState.observe(requireActivity(), Observer {
            btn_settings_dog_edit.isEnabled = it
            Log.d("zzz", "observe: $it")
            if (it) btn_settings_dog_edit.setBackgroundColor(requireActivity().getColor(R.color.color_aa5900))
            else btn_settings_dog_edit.setBackgroundColor(requireActivity().getColor(R.color.color_e7d0b7))
        })
    }



    private fun clickBackButton(){
        (activity as SettingsActivity).makeCurrentFragment(SettingFragment())
    }

}
