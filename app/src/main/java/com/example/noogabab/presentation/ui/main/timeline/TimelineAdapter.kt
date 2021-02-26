package com.example.noogabab.presentation.ui.main.timeline

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.noogabab.R
import com.example.noogabab.presentation.entity.PresenterTimeLine
import kotlinx.android.synthetic.main.item_time_line.view.*
import java.util.*
import kotlin.collections.ArrayList

class TimelineAdapter(private val itemList: ArrayList<PresenterTimeLine>): RecyclerView.Adapter<TimelineViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        val inflate = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_time_line, parent, false)

        return TimelineViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(
            itemList.getOrNull(position - 1),
            item,
            itemList.getOrNull(position + 1)
        )
    }
    override fun getItemCount() = itemList.size
}

class TimelineViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    fun bind(prev: PresenterTimeLine?, item: PresenterTimeLine, next: PresenterTimeLine?) {
        with(view) {
            val subContentArr = item.subContent.split(" ")
            txt_timeline_time.text = formatDate(item.time, "HH:mm")
            img_timeline.setImageDrawable(item.icon)
            txt_timeline_content.text = item.content
            txt_timeline_sub_content.text = Html.fromHtml("<b>" + subContentArr[0] +"</b> " + subContentArr[1])
            txt_timeline_day.text = getDateString(item.time)

            // Show divider or not
            div_timeline_upper.isVisible = (prev != null)
            div_timeline_lower.isVisible = (next != null)

            // Show date or not
            txt_timeline_day.isVisible = shouldWeShowDate(prev, item)
        }
    }

    private fun formatDate(timestamp: Long, format: String): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp

        return SimpleDateFormat(format, Locale.getDefault()).format(timestamp).toString()
    }

    private fun shouldWeShowDate(prev: PresenterTimeLine?, item: PresenterTimeLine): Boolean {
        val isMostRecent = (prev == null)

        return isMostRecent || isItemDateBeforePrevDate(prev, item) // prev -> item -> next
    }

    private fun isItemDateBeforePrevDate(prev: PresenterTimeLine?, item: PresenterTimeLine): Boolean {
        prev ?: return false

        val prevDate = Date(prev.time)
        val itemDate = Date(item.time)
        return (prevDate.day != itemDate.day)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateString(timestamp: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd 00:00:00")
        val t = sdf.format(timestamp)
        var today = Calendar.getInstance()

        val nowDate = today.time.time
        val thenDate = sdf.parse(t).time
        val calcDate = (nowDate - thenDate) / (60 * 60 * 24 * 1000)

        return when {
            thenDate > nowDate -> view.context.getString(R.string.app_name)
            calcDate == 0L -> view.context.getString(R.string.today)
            calcDate == 1L -> view.context.getString(R.string.yesterday)
            else -> view.context.getString(R.string.n_days_before, calcDate)
        }
    }



}