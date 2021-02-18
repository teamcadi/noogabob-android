package com.example.noogabab.presentation.ui.main.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.noogabab.R
import com.example.noogabab.presentation.dialog.AlertDialog
import com.example.noogabab.presentation.ui.main.MainActivity
import com.example.noogabab.presentation.ui.main.MainViewModel
import com.example.noogabab.presentation.ui.main.album.AlbumFragment
import com.example.noogabab.presentation.ui.main.timeline.TimelineActivity
import com.example.noogabab.util.SharedProfile
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor = Color.parseColor("#ffc176")

        getProfile()
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

    private fun getProfile() {
        val shared = activity?.getSharedPreferences(SharedProfile.NAME, Context.MODE_PRIVATE)
        val image = shared?.getString(SharedProfile.IMAGE_KEY, "")
        if (image == "") Glide.with(requireActivity()).load(R.drawable.ic_default_profile)
            .centerCrop().into(iv_main)
        else Glide.with(requireActivity()).load(image).centerCrop().into(iv_main)
    }

    private fun clickTimeline() {
        val i = Intent(requireContext(), TimelineActivity::class.java)
        startActivity(i)
        activity?.overridePendingTransition(R.anim.animation_slide_down, R.anim.animation_slide_up)
    }

    private fun clickChangeProfile() {
        (activity as MainActivity).changeFragment(R.id.navi_album)
    }

    private fun clickFeedBob() {
        AlertDialog().defaultAlert(
            requireContext(), "밥을 먹일까요?", "선택한 식사는 취소가 불가능합니다.", "확인", "취소", {
                Log.d("www", "clickFeedBob: ok")
            }, {
                Log.d("www", "clickFeedBob: cancel")
            })
    }

    private fun clickFeedSnack() {
        AlertDialog().defaultAlert(
            requireContext(), "간식을 먹일까요?", "선택한 식사는 취소가 불가능합니다.", "확인", "취소", {
                Log.d("www", "clickFeedSnack: ok")
            }, {
                Log.d("www", "clickFeedSnack: cancel")
            })
    }
}