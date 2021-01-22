package com.example.noogabab.presentation.ui.start.create

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.noogabab.R
import com.example.noogabab.presentation.entity.PresenterBabTime
import kotlinx.android.synthetic.main.item_bab_time.view.*


class CreateDogListAdapter(private val items: MutableList<PresenterBabTime>): BaseAdapter() {
    override fun getCount() = items.size
    override fun getItem(p: Int) = items[p]
    override fun getItemId(p: Int) = p.toLong()
    override fun getView(p: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view
        if (convertView == null) convertView = LayoutInflater.from(parent?.context).inflate(R.layout.item_bab_time, parent, false)
        val item: PresenterBabTime = items[p]
        convertView!!.txt_count_bab.text = item.bab
        convertView.txt_bab_meridiem.text = item.meridiem
        convertView.txt_bab_time.text = item.time
        return convertView
    }

}