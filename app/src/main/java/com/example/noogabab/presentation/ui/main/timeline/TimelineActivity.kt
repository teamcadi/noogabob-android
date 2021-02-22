package com.example.noogabab.presentation.ui.main.timeline

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noogabab.R
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.data.api.model.TimelineData
import com.example.noogabab.presentation.entity.PresenterTimeLine
import com.example.noogabab.util.SharedGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_time_line.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TimelineActivity : AppCompatActivity() {
    private val timelineViewModel by viewModels<TimelineViewModel>()
    private lateinit var sharedGroup: SharedPreferences
    private lateinit var key: String
    private var groupId: Int = -1

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line)

        load()
//        test()
        observe()
    }

    private fun load() {
        sharedGroup = getSharedPreferences(SharedGroup.NAME, Context.MODE_PRIVATE)
        key = sharedGroup.getString(SharedGroup.GROUP_UUID_KEY, "")!!
        groupId = sharedGroup.getInt(SharedGroup.GROUP_ID_KEY, -1)
        btn_timeline_close.setOnClickListener { finish() }
    }

    private fun test() {
        val dummy = ArrayList<TimelineData>()
        dummy.add(TimelineData("밥", "나 홍길동", 1613040705782, 0))
        dummy.add(TimelineData("간식", "나 홍길동", 1613040705782, 1))
        dummy.add(TimelineData("밥", "나 홍길동", 1613040705782, 0))
        dummy.add(TimelineData("밥", "나 홍길동", 1612990705782, 0))
        dummy.add(TimelineData("간식", "나 홍길동", 1612990705782, 1))
        dummy.add(TimelineData("밥", "나 홍길동", 1613040705782, 0))
        dummy.add(TimelineData("밥", "나 홍길동", 1612776760668, 0))
        dummy.add(TimelineData("밥", "나 홍길동", 1612776760668, 0))
        dummy.add(TimelineData("밥", "나 홍길동", 1612776760668, 0))
        dummy.add(TimelineData("간식", "나 홍길동", 1612275760668, 1))
        dummy.add(TimelineData("간식", "나 홍길동", 1612275760668, 1))
        dummy.add(TimelineData("간식", "나 홍길동", 1612275760668, 1))
        dummy.add(TimelineData("간식", "나 홍길동", 1612275760668, 1))
        dummy.add(TimelineData("밥", "나 홍길동", 1612275760668, 0))
        dummy.add(TimelineData("밥", "나 홍길동", 1612175760668, 0))
        dummy.add(TimelineData("밥", "나 홍길동", 1612175760668, 0))
        CoroutineScope(Dispatchers.Main).launch {
            progress_timeline_loading.visibility = View.VISIBLE
            val data = setPresenterTimeline(dummy)
            delay(2000)
            setRecyclerView(data)
            progress_timeline_loading.visibility = View.GONE
        }
    }

    private fun observe() {
        val timeline = timelineViewModel.getTimeline(key, groupId)
        timeline.observe(this, { resultData ->
            when (resultData) {
                is ResultData.Loading -> {
                    progress_timeline_loading.visibility = View.VISIBLE
                }
                is ResultData.Success -> {
                    progress_timeline_loading.visibility = View.GONE
                    setRecyclerView(setPresenterTimeline(resultData.data!!.data!!))
                }
                is ResultData.Failed -> {
                    progress_timeline_loading.visibility = View.GONE
                    Toast.makeText(this, getString(R.string.toast_server_failed), Toast.LENGTH_SHORT).show()
                }
                is ResultData.Exception -> {
                    progress_timeline_loading.visibility = View.GONE
                    Toast.makeText(this, getString(R.string.toast_server_failed), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setRecyclerView(list: ArrayList<PresenterTimeLine>) {
        if (list.isEmpty()) {
            txt_timline_empty.isVisible = true
        }
        else {
            txt_timline_empty.isVisible = false
            val adapter = TimelineAdapter(list)
            recycler_timeline.layoutManager = LinearLayoutManager(this)
            recycler_timeline.adapter = adapter
        }
    }

    private fun setPresenterTimeline(list: ArrayList<TimelineData>): ArrayList<PresenterTimeLine> {
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