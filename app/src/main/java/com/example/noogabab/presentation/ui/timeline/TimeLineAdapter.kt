package com.example.noogabab.presentation.ui.timeline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noogabab.R
import com.example.noogabab.presentation.entity.PresenterTimeLine
import kotlinx.android.synthetic.main.item_time_line.view.*

class TimeLineAdapter(private val itemList: ArrayList<PresenterTimeLine>): RecyclerView.Adapter<TimeLineViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_time_line, parent, false)
        return TimeLineViewHolder(inflate)
    }
    override fun onBindViewHolder(holder: TimeLineViewHolder, position: Int) {
        val item = itemList[position]
        holder.apply {
            bind(item)
        }
    }
    override fun getItemCount() = itemList.size
}

class TimeLineViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var view = itemView
    fun bind(item: PresenterTimeLine) {
        view.txt_timeline_time.text = item.time
        view.img_timeline.setImageDrawable(item.icon)
        view.txt_timeline_content.text = item.content
        view.txt_timeline_sub_content.text = item.subContent
    }
}