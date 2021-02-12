package com.example.noogabab.presentation.ui.main.timeline

import android.icu.text.SimpleDateFormat
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.noogabab.R
import com.example.noogabab.presentation.entity.PresenterTimeLine
import kotlinx.android.synthetic.main.item_time_line2.view.*
import java.util.*
import kotlin.collections.ArrayList

class TimelineAdapter(private val itemList: ArrayList<PresenterTimeLine>): RecyclerView.Adapter<TimelineViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        val inflate = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_time_line2, parent, false)

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
//            val subContentArr = item.subContent.split(" ")
            txt_timeline_time.text = formatDate(item.time, "hh:mm")
            img_timeline.setImageDrawable(item.icon)
            txt_timeline_content.text = item.content
            txt_timeline_sub_content.text = item.subContent
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

        return itemDate.before(prevDate)
    }

    private fun getDateString(timestamp: Long): String {
        val nowDate = Date().time / 1000 / 60 / 60 / 24
        val thenDate = timestamp / 1000 / 60 / 60 / 24

        return when {
            thenDate > nowDate -> view.context.getString(R.string.app_name)
            thenDate == nowDate -> view.context.getString(R.string.today)
            thenDate == nowDate - 1 -> view.context.getString(R.string.yesterday)
            else -> view.context.getString(R.string.n_days_before, nowDate - thenDate)
        }
    }

}