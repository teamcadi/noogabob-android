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
        txt_time_line.setOnClickListener(this)
        iv_select.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view) {
            txt_time_line -> clickTimeline()
            iv_select -> clickChangeProfile()
        }
    }

    private fun getProfile() {
        val shared = activity?.getSharedPreferences(SharedProfile.NAME, Context.MODE_PRIVATE)
        val image = shared?.getString(SharedProfile.IMAGE_KEY, "")
        if (image == "") Glide.with(requireActivity()).load(R.drawable.ic_default_profile).centerCrop().into(iv_main)
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
}