package com.example.noogabab.presentation.ui.start.createGroup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.noogabab.R
import com.example.noogabab.presentation.entity.PresenterBabTime
import kotlinx.android.synthetic.main.item_bob_time.view.*

class BobTimeListAdapter : BaseAdapter() {
    private val items = ArrayList<PresenterBabTime>()

    override fun getCount() = items.size
    override fun getItem(p: Int) = items[p]
    override fun getItemId(p: Int) = p.toLong()
    override fun getView(p: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view
        if (convertView == null) convertView =
            LayoutInflater.from(parent?.context).inflate(R.layout.item_bob_time, parent, false)
        val item: PresenterBabTime = items[p]
        convertView!!.txt_count_bob.text = item.bob
        convertView.txt_meridiem_bob.text = item.meridiem
        convertView.txt_time_bob.text = item.time
        return convertView
    }

    fun addItem(item: PresenterBabTime) {
        items.add(item)
    }

    fun deleteItem(p: Int) {
        items.removeAt(p)
    }

    fun setItem(p: Int, bob: String, meridiem: String, time: String) {
        items[p] = PresenterBabTime(bob, meridiem, time)
    }
}