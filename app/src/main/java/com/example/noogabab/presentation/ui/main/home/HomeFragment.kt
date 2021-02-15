package com.example.noogabab.presentation.ui.main.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.main.MainActivity
import com.example.noogabab.presentation.ui.main.album.AlbumFragment
import com.example.noogabab.presentation.ui.main.timeline.TimelineActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor = Color.parseColor("#ffc176")

        txt_time_line.setOnClickListener(this)
        iv_select.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view) {
            txt_time_line -> clickTimeline()
            iv_select -> clickChangeProfile()
        }
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