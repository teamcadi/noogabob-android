package com.example.noogabab.presentation.ui.timeline

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noogabab.R
import com.example.noogabab.presentation.entity.PresenterTimeLine
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_time_line.*

@AndroidEntryPoint
class TimeLineActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line)

        val list = ArrayList<PresenterTimeLine>()
        list.add(PresenterTimeLine("17:30", getDrawable(R.drawable.ic_alarm_timeline)!!, "밥", "곧 밥을 먹는다"))
        list.add(PresenterTimeLine("17:30", getDrawable(R.drawable.ic_bab_timeline)!!, "밥", "곧 밥을 먹는다"))
        list.add(PresenterTimeLine("17:30", getDrawable(R.drawable.ic_bab_timeline)!!, "밥", "곧 밥을 먹는다"))
        list.add(PresenterTimeLine("17:30", getDrawable(R.drawable.ic_bab_timeline)!!, "밥", "곧 밥을 먹는다"))
        list.add(PresenterTimeLine("17:30", getDrawable(R.drawable.ic_bab_timeline)!!, "밥", "곧 밥을 먹는다"))
        list.add(PresenterTimeLine("17:30", getDrawable(R.drawable.ic_snack_timeline)!!, "밥", "곧 밥을 먹는다"))
        list.add(PresenterTimeLine("17:30", getDrawable(R.drawable.ic_bab_timeline)!!, "밥", "곧 밥을 먹는다"))
        list.add(PresenterTimeLine("17:30", getDrawable(R.drawable.ic_bab_timeline)!!, "밥", "곧 밥을 먹는다"))
        list.add(PresenterTimeLine("17:30", getDrawable(R.drawable.ic_bab_timeline)!!, "밥", "곧 밥을 먹는다"))
        list.add(PresenterTimeLine("17:30", getDrawable(R.drawable.ic_alarm_timeline)!!, "밥", "곧 밥을 먹는다"))
        list.add(PresenterTimeLine("17:30", getDrawable(R.drawable.ic_alarm_timeline)!!, "밥", "곧 밥을 먹는다"))
        list.add(PresenterTimeLine("17:30", getDrawable(R.drawable.ic_alarm_timeline)!!, "밥", "곧 밥을 먹는다"))
        list.add(PresenterTimeLine("17:30", getDrawable(R.drawable.ic_alarm_timeline)!!, "밥", "곧 밥을 먹는다"))


        val adapter = TimeLineAdapter(list)
        recycler_timeline.layoutManager = LinearLayoutManager(this)
        recycler_timeline.adapter = adapter
    }

}