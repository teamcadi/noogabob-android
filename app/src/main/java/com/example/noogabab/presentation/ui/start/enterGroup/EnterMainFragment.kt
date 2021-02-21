package com.example.noogabab.presentation.ui.start.enterGroup

import android.content.Context
import android.content.Intent
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
import com.example.noogabab.presentation.ui.start.BobTimeView
import com.example.noogabab.util.SharedDog
import com.example.noogabab.util.SharedGroup
import com.example.noogabab.util.SharedProfile
import com.example.noogabab.util.SharedUser
import kotlinx.android.synthetic.main.fragment_enter_main.*

class EnterMainFragment :
    Fragment(R.layout.fragment_enter_main), View.OnClickListener {
    private val enterGroupViewModel: EnterGroupViewModel by activityViewModels<EnterGroupViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        load()
        observe()
    }

    private fun load() {
        btn_enter_group.setOnClickListener(this)
        val sharedDog = requireActivity().getSharedPreferences(SharedDog.NAME, Context.MODE_PRIVATE)
        readonly_dog_name.setText(sharedDog.getString(SharedDog.DOG_NAME_KEY, ""))
        readonly_dog_age.setText(sharedDog.getInt(SharedDog.DOG_AGE_KEY, 0).toString())
        readonly_dog_kind.setText(sharedDog.getString(SharedDog.DOG_KIND_KEY, ""))
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
            readonly_linear_bob_time.addView(bobTime)
        }
    }

    private fun observe() {
        enterGroupViewModel.currentBtnState.observe(requireActivity(), Observer {
            btn_enter_group.isEnabled = it
            if (it) btn_enter_group.setBackgroundColor(requireActivity().getColor(R.color.color_aa5900))
            else btn_enter_group.setBackgroundColor(requireActivity().getColor(R.color.color_e7d0b7))
        })
    }

    override fun onClick(p0: View?) {
        val sharedUser = requireActivity().getSharedPreferences(SharedUser.NAME, Context.MODE_PRIVATE)
        val sharedGroup =
            requireActivity().getSharedPreferences(SharedGroup.NAME, Context.MODE_PRIVATE)
        val userEditor = sharedUser.edit()
        val groupEditor = sharedGroup.edit()
        enterGroupViewModel.createUser(sharedGroup.getString(SharedGroup.GROUP_UUID_KEY, "")!!)
            .observe(requireActivity(), Observer { resultData ->
                when(resultData) {
                    is ResultData.Loading -> {}
                    is ResultData.Success -> {
                        resultData.data!!.createUserData!!.apply {
                            groupEditor.putInt(SharedGroup.GROUP_ID_KEY, groupId!!)
                            userEditor.putInt(SharedUser.USER_ID_KEY, userId!!)
                            groupEditor.apply()
                            userEditor.apply()
                        }
                        startActivity(Intent(activity, MainActivity::class.java))
                    }
                    else -> {
                        Toast.makeText(context, activity?.getString(R.string.toast_server_failed), Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }
}