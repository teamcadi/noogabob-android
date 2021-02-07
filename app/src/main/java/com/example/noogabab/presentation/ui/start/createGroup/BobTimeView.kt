package com.example.noogabab.presentation.ui.start.createGroup

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.noogabab.R
import kotlinx.android.synthetic.main.item_bob_time.view.*

class BobTimeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    isClose: Boolean = false
) : LinearLayout(context, attrs, defStyleAttr) {
    init {
        inflate(context, R.layout.item_bob_time, this)
        if (!isClose) {
            btn_time_close.isEnabled = isClose
            btn_time_close.visibility = View.INVISIBLE
        }else {
            btn_time_close.isEnabled = isClose
            btn_time_close.visibility = View.VISIBLE
        }
    }

    fun setCountBob(text: String) {
        txt_count_bob.text = text
    }

    fun setMeridiemBob(text: String) {
        txt_meridiem_bob.text = text
    }

    fun setTimeBob(text: String) {
        txt_time_bob.text = text
    }



}