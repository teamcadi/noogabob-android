package com.example.noogabab.presentation.ui.main.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.noogabab.R
import com.example.noogabab.presentation.dialog.AlertDialog
import com.example.noogabab.presentation.ui.main.MainActivity
import com.example.noogabab.presentation.ui.main.MainViewModel
import com.example.noogabab.presentation.ui.main.timeline.TimelineActivity
import com.example.noogabab.util.SharedDog
import com.example.noogabab.util.SharedProfile
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {
    private val mainViewModel: MainViewModel by activityViewModels<MainViewModel>()
    private lateinit var sharedDog: SharedPreferences
    private lateinit var sharedProfile: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedDog = requireActivity().getSharedPreferences(SharedDog.NAME, Context.MODE_PRIVATE)
        sharedProfile = requireActivity().getSharedPreferences(SharedProfile.NAME, Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        load()
        btn_eat.setOnClickListener(this)
        btn_snack.setOnClickListener(this)
        txt_time_line.setOnClickListener(this)
        iv_select.setOnClickListener(this)
    }

    private fun load() {
        requireActivity().window.statusBarColor = Color.parseColor("#ffc176")
        txt_dog_name.text = sharedDog.getString(SharedDog.DOG_NAME_KEY, "")
        val image = sharedProfile.getString(SharedProfile.IMAGE_KEY, "")
        if (image == "") Glide.with(requireActivity()).load(R.drawable.ic_default_profile)
            .centerCrop().into(iv_main)
        else Glide.with(requireActivity()).load(image).error(R.drawable.ic_default_profile)
            .centerCrop().into(iv_main)
    }

    override fun onClick(view: View?) {
        when (view) {
            txt_time_line -> clickTimeline()
            iv_select -> clickChangeProfile()
            btn_eat -> clickFeedBob()
            btn_snack -> clickFeedSnack()
        }
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
            requireContext(), "밥을 먹일까요?", "선택한 식사는 취소가 불가능합니다.", "확인", "취소", {
            }, {
            })
    }

    private fun clickFeedSnack() {
        AlertDialog().defaultAlert(
            requireContext(), "간식을 먹일까요?", "선택한 식사는 취소가 불가능합니다.", "확인", "취소", {
            }, {
            })
    }
}