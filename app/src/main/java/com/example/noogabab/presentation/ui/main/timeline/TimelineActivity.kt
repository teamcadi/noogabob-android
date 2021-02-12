package com.example.noogabab.presentation.ui.main.timeline

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TimelineActivity : AppCompatActivity() {
    private val timelineViewModel by viewModels<TimelineViewModel>()

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line)

        btn_timeline_close.setOnClickListener { finish() }
        test()
//        observe()
    }

    private fun test() {
        val dummy = ArrayList<Timeline>()
        dummy.add(Timeline("밥", "나 홍길동", 1613040705782, 0))
        dummy.add(Timeline("간식", "나 홍길동", 1613040705782, 1))
        dummy.add(Timeline("밥", "나 홍길동", 1613040705782, 0))
        dummy.add(Timeline("밥", "나 홍길동", 1612990705782, 0))
        dummy.add(Timeline("간식", "나 홍길동", 1612990705782, 1))
        dummy.add(Timeline("밥", "나 홍길동", 1613040705782, 0))
        dummy.add(Timeline("밥", "나 홍길동", 1612776760668, 0))
        dummy.add(Timeline("밥", "나 홍길동", 1612776760668, 0))
        dummy.add(Timeline("밥", "나 홍길동", 1612776760668, 0))
        dummy.add(Timeline("간식", "나 홍길동", 1612275760668, 1))
        dummy.add(Timeline("간식", "나 홍길동", 1612275760668, 1))
        dummy.add(Timeline("간식", "나 홍길동", 1612275760668, 1))
        dummy.add(Timeline("간식", "나 홍길동", 1612275760668, 1))
        dummy.add(Timeline("밥", "나 홍길동", 1612275760668, 0))
        dummy.add(Timeline("밥", "나 홍길동", 1612175760668, 0))
        dummy.add(Timeline("밥", "나 홍길동", 1612175760668, 0))
        CoroutineScope(Dispatchers.Main).launch {
            progress_timeline_loading.visibility = View.VISIBLE
            val data = setPresenterTimeline(dummy)
            delay(2000)
            setRecyclerView(data)
            progress_timeline_loading.visibility = View.GONE
        }
    }

    private fun observe() {
        val timeline = timelineViewModel.getTimeline()
        timeline.observe(this, { resultData ->
            when (resultData) {
                is ResultData.Loading -> {
                    progress_timeline_loading.visibility = View.VISIBLE
                }
                is ResultData.Success -> {
                    progress_timeline_loading.visibility = View.GONE

                    val timelineData = resultData.data
                    if (timelineData != null) {
                        setRecyclerView(setPresenterTimeline(timelineData.data as ArrayList<Timeline>))
                    }
                }
                is ResultData.Failed -> {
                    progress_timeline_loading.visibility = View.GONE
                    // todo: 실패 처리
                }
                is ResultData.Exception -> {
                    progress_timeline_loading.visibility = View.GONE

                }
            }
        })
    }

    private fun setRecyclerView(list: ArrayList<PresenterTimeLine>) {
        val adapter = TimelineAdapter(list)
        recycler_timeline.layoutManager = LinearLayoutManager(this)
        recycler_timeline.adapter = adapter
    }

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