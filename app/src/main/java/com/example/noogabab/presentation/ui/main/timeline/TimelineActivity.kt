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
        observe()
    }

    private fun load() {
        sharedGroup = getSharedPreferences(SharedGroup.NAME, Context.MODE_PRIVATE)
        key = sharedGroup.getString(SharedGroup.GROUP_UUID_KEY, "")!!
        groupId = sharedGroup.getInt(SharedGroup.GROUP_ID_KEY, -1)
        btn_timeline_close.setOnClickListener { finish() }
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
                    Toast.makeText(this, "서버가 불안정합니다.", Toast.LENGTH_SHORT).show()
                }
                is ResultData.Exception -> {
                    progress_timeline_loading.visibility = View.GONE
                    Toast.makeText(this, "서버가 불안정합니다.", Toast.LENGTH_SHORT).show()
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