package com.example.noogabab.presentation.ui.main.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.noogabab.R
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.data.api.request.FeedRequest
import com.example.noogabab.presentation.dialog.AlertDialog
import com.example.noogabab.presentation.ui.main.MainActivity
import com.example.noogabab.presentation.ui.main.MainViewModel
import com.example.noogabab.presentation.ui.main.timeline.TimelineActivity
import com.example.noogabab.util.SharedDog
import com.example.noogabab.util.SharedGroup
import com.example.noogabab.util.SharedProfile
import com.example.noogabab.util.SharedUser
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {
    private val homeViewModel: HomeViewModel by activityViewModels<HomeViewModel>()
    private lateinit var sharedDog: SharedPreferences
    private lateinit var sharedProfile: SharedPreferences
    private lateinit var sharedGroup: SharedPreferences
    private lateinit var sharedUser: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedDog = requireActivity().getSharedPreferences(SharedDog.NAME, Context.MODE_PRIVATE)
        sharedProfile =
            requireActivity().getSharedPreferences(SharedProfile.NAME, Context.MODE_PRIVATE)
        sharedGroup = requireActivity().getSharedPreferences(SharedGroup.NAME, Context.MODE_PRIVATE)
        sharedUser = requireActivity().getSharedPreferences(SharedUser.NAME, Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        load()
        observe()
    }

    private fun load() {
        requireActivity().window.statusBarColor = Color.parseColor("#ffc176")
        txt_dog_name.text = sharedDog.getString(SharedDog.DOG_NAME_KEY, "")
        val image = sharedProfile.getString(SharedProfile.IMAGE_KEY, "")
        if (image == "") Glide.with(requireActivity()).load(R.drawable.ic_default_profile)
            .centerCrop().into(iv_main)
        else Glide.with(requireActivity()).load(image).error(R.drawable.ic_default_profile)
            .centerCrop().into(iv_main)
        btn_eat.setOnClickListener(this)
        btn_snack.setOnClickListener(this)
        txt_time_line.setOnClickListener(this)
        iv_select.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            txt_time_line -> clickTimeline()
            iv_select -> clickChangeProfile()
            btn_eat -> clickFeedBob()
            btn_snack -> clickFeedSnack()
        }
    }

    private fun observe() {
        getLatest()
        homeViewModel.currentMealLatest.observe(requireActivity(), Observer {
            txt_status_time.text = it
        })
    }

    private fun getLatest() {
        val dogId = sharedDog.getInt(SharedDog.DOG_ID_KEY, -1)
        if (dogId == -1) return
        homeViewModel.getMealLatest(dogId).observe(requireActivity(), Observer { resultData ->
            when (resultData) {
                is ResultData.Loading -> homeViewModel.updateMealLatest("로딩 중")
                is ResultData.Success -> homeViewModel.updateMealLatest(resultData.data!!.content!!)
                else -> homeViewModel.updateMealLatest("로딩 중")
            }
        })
    }

    private fun clickTimeline() {
        startActivity(Intent(requireContext(), TimelineActivity::class.java))
        activity?.overridePendingTransition(R.anim.animation_slide_down, R.anim.animation_slide_up)
    }

    private fun clickChangeProfile() {
        (activity as MainActivity).changeFragment(R.id.navi_album)
    }

    private fun clickFeedBob() {
        AlertDialog().defaultAlert(
            requireContext(),
            "밥을 먹일까요?",
            "선택한 식사는 취소가 불가능합니다.",
            "확인",
            "취소", {
                homeViewModel.feedMeal(
                    sharedGroup.getString(SharedGroup.GROUP_UUID_KEY, "")!!,
                    sharedDog.getInt(SharedDog.DOG_ID_KEY, -1),
                    FeedRequest(sharedUser.getInt(SharedUser.USER_ID_KEY, -1))
                ).observe(requireActivity(), Observer { resultData ->
                    when (resultData) {
                        is ResultData.Loading -> {
                        }
                        is ResultData.Success -> {
                            Toast.makeText(requireContext(), "밥 주기 완료!", Toast.LENGTH_SHORT).show()
                            getLatest()
                        }
                        else -> {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.toast_server_exception),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                })
            }, {})
    }

    private fun clickFeedSnack() {
        AlertDialog().defaultAlert(
            requireContext(), "간식을 먹일까요?", "선택한 식사는 취소가 불가능합니다.", "확인", "취소", {
                homeViewModel.feedSnack(
                    sharedGroup.getString(SharedGroup.GROUP_UUID_KEY, "")!!,
                    sharedDog.getInt(SharedDog.DOG_ID_KEY, -1),
                    FeedRequest(sharedUser.getInt(SharedUser.USER_ID_KEY, -1))
                ).observe(requireActivity(), Observer { resultData ->
                    when (resultData) {
                        is ResultData.Loading -> {
                        }
                        is ResultData.Success -> {
                            Toast.makeText(requireContext(), "간식 주기 완료!", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.toast_server_exception),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                })
            }, {
            })
    }
}