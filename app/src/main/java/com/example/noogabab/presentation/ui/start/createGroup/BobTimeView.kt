package com.example.noogabab.presentation.ui.start.createGroup

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.noogabab.R
import kotlinx.android.synthetic.main.item_bob_time.view.*

class BobTimeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {
    init {
        inflate(context, R.layout.item_bob_time, this)
    }

    fun setCountBob(text: String) {
        txt_count_bob.text = text
    }

    fun getCountBob() = txt_count_bob.text.toString()

    fun setMeridiemBob(text: String) {
        txt_meridiem_bob.text = text
    }

    fun setTimeBob(text: String) {
        txt_time_bob.text = text
    }
}