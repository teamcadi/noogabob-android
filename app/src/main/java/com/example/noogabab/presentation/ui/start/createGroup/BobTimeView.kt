package com.example.noogabab.presentation.ui.start.createGroup

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.FragmentManager
import com.example.noogabab.R
import com.example.noogabab.presentation.entity.PresenterBobTime
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.item_bob_time.view.*

class BobTimeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    private val supportFM: FragmentManager,
    private val viewModel: CreateGroupViewModel
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {
    init {
        inflate(context, R.layout.item_bob_time, this)
        txt_count_bob.setOnClickListener(this)
        txt_meridiem_bob.setOnClickListener(this)
        txt_time_bob.setOnClickListener(this)
        btn_remove_bob.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            btn_remove_bob -> clickRemoveBob()
            else -> clickBob()
        }
    }

    fun setRemoveInVisible() {
        btn_remove_bob.visibility = View.INVISIBLE
    }

    fun setCountBob(text: String) {
        txt_count_bob.text = text
    }

    private fun clickRemoveBob() {
        val parentView = parent as LinearLayout
        val index = if (txt_count_bob.text == "두 끼") 1 else 2
        parentView.removeViewAt(index)
        val lastView = parentView[parentView.size - 1] as BobTimeView
        if (index == 1 && parentView.size == 2) lastView.setCountBob("두 끼")
    }

    private fun clickBob() {
        val timePicker = MaterialTimePicker.Builder()
            .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .build()
        timePicker.show(
            supportFM,
            "createGroup"
        )
        timePicker.addOnPositiveButtonClickListener {
            var meridiem = "오전"
            var hour: Int = timePicker.hour
            val minute = timePicker.minute
            if (hour > 12) {
                hour -= 12
                meridiem = "오후"
            }
            val strHour = if (hour.toString().length == 1) "0$hour" else hour.toString()
            val strMinute =
                if (minute.toString().length == 1) "0$minute" else minute.toString()
            txt_meridiem_bob.text = meridiem
            txt_time_bob.text = "${strHour}:${strMinute}"
            viewModel.updateBobTimes(
                PresenterBobTime(
                    txt_count_bob.text as String,
                    meridiem,
                    "${strHour}:${strMinute}"
                )
            )
        }
    }
}