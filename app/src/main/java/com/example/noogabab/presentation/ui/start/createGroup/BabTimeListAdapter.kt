package com.example.noogabab.presentation.ui.start.createGroup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.noogabab.R
import com.example.noogabab.presentation.entity.PresenterBabTime
import kotlinx.android.synthetic.main.item_bab_time.view.*

class BabTimeListAdapter : BaseAdapter() {
    private val items = ArrayList<PresenterBabTime>()

    override fun getCount() = items.size
    override fun getItem(p: Int) = items[p]
    override fun getItemId(p: Int) = p.toLong()
    override fun getView(p: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view
        if (convertView == null) convertView =
            LayoutInflater.from(parent?.context).inflate(R.layout.item_bab_time, parent, false)
        val item: PresenterBabTime = items[p]
        convertView!!.txt_count_bab.text = item.bab
        convertView.txt_bab_meridiem.text = item.meridiem
        convertView.txt_bab_time.text = item.time
        return convertView
    }

    fun addItem(item: PresenterBabTime) {
        items.add(item)
    }

    fun deleteItem(p: Int) {
        items.removeAt(p)
    }

    fun setItem(p: Int, bab: String, meridiem: String, time: String) {
        items[p] = PresenterBabTime(bab, meridiem, time)
    }
}