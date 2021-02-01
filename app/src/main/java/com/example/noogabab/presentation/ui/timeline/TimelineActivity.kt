package com.example.noogabab.presentation.ui.timeline

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noogabab.R
import com.example.noogabab.data.model.ResultData
import com.example.noogabab.data.model.Timeline
import com.example.noogabab.presentation.entity.PresenterTimeLine
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_time_line.*

@AndroidEntryPoint
class TimelineActivity : AppCompatActivity() {
    private val timelineViewModel by viewModels<TimelineViewModel>()


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line)

        setApiTimeline()
    }

    private fun setApiTimeline() {
        val timeline = timelineViewModel.getTimeline()
        timeline.observe(this, Observer { resultData ->
            when (resultData) {
                is ResultData.Loading -> {
                    Toast.makeText(this, "로딩중", Toast.LENGTH_SHORT).show()
                }
                is ResultData.Success -> {
                    Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
                    val timelineData = resultData.data
                    if (timelineData != null) {
                        setRecyclerView(setPresenterTimeline(timelineData.data as ArrayList<Timeline>))
                    }
                }
                is ResultData.Failed -> {
                    Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
                }
                is ResultData.Exception -> {
                }
            }
        })
    }

    private fun setRecyclerView(list: ArrayList<PresenterTimeLine>) {
        val adapter = TimelineAdapter(list)
        recycler_timeline.layoutManager = LinearLayoutManager(this)
        recycler_timeline.adapter = adapter
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setPresenterTimeline(list: ArrayList<Timeline>): ArrayList<PresenterTimeLine> {
        val presenterTimeLineList = ArrayList<PresenterTimeLine>()
        for (item in list) {
            val icon = when(item.type) {
                0 -> getDrawable(R.drawable.ic_bab_timeline)!!
                1 ->  getDrawable(R.drawable.ic_snack_timeline)!!
                2 ->  getDrawable(R.drawable.ic_alarm_timeline)!!
                else -> getDrawable(R.drawable.ic_bab_timeline)!!
            }
            presenterTimeLineList.add(PresenterTimeLine(item.time, icon, item.content, item.subContent))
        }
        return presenterTimeLineList
    }
}