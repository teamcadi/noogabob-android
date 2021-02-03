package com.example.noogabab.presentation.ui.timeline

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noogabab.R
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.data.api.model.Timeline
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
        timeline.observe(this, { resultData ->
            when (resultData) {
                is ResultData.Loading -> {
                    prograss_loading.visibility = View.VISIBLE
                }
                is ResultData.Success -> {
                    prograss_loading.visibility = View.GONE

                    val timelineData = resultData.data
                    if (timelineData != null) {
                        setRecyclerView(setPresenterTimeline(timelineData.data as ArrayList<Timeline>))
                    }
                }
                is ResultData.Failed -> {
                    prograss_loading.visibility = View.GONE

                }
                is ResultData.Exception -> {
                    prograss_loading.visibility = View.GONE

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