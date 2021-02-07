package com.example.noogabab.presentation.ui.start.createGroup

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.example.noogabab.R
import com.example.noogabab.presentation.dialog.CreateGroupDialog

import com.example.noogabab.presentation.entity.PresenterBabTime
import com.example.noogabab.util.DynamicTextWatcher
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_create_group.*
import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.android.synthetic.main.activity_time_line.*
import kotlinx.android.synthetic.main.fragment_dialog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CreateGroupActivity : AppCompatActivity() {
    private lateinit var adapter: BabTimeListAdapter
    private val textWatcher = DynamicTextWatcher(
        onChanged = { _, _, _, _ ->
            if (edit_dog_age.text.toString() == "" ||
                edit_dog_name.text.toString() == "" ||
                edit_dog_kind.text.toString() == ""
            ) {
                btn_get_key.isEnabled = false
                btn_get_key.setBackgroundColor(Color.LTGRAY)
            } else {
                btn_get_key.isEnabled = true
                btn_get_key.setBackgroundColor(applicationContext.resources.getColor(R.color.color_aa5900))
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_group)

        edit_dog_age.addTextChangedListener(textWatcher)
        edit_dog_name.addTextChangedListener(textWatcher)
        edit_dog_kind.addTextChangedListener(textWatcher)
        loadDogKind()
        loadBabTime()
        addBabTime()
        clickBabTime()
        getKeyClick()
    }

    private fun getKeyClick() {
        btn_get_key.setOnClickListener {
            val dialog = CreateGroupDialog(this@CreateGroupActivity)
            CoroutineScope(Main).launch {
                dialog.show()
                delay(2000)
                // 서버 호출
                dialog.progress_dialog.visibility = View.INVISIBLE
                dialog.btn_dialog_close.visibility = View.VISIBLE
                dialog.txt_dialog_content.text = "발급 완료!"
                dialog.txt_dialog_key.visibility = View.VISIBLE
                dialog.btn_dialog_clone.visibility = View.VISIBLE
            }
        }
    }

    private fun loadDogKind() {
        val dogs = resources.getStringArray(R.array.dogs)
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dogs)
        edit_dog_kind.setAdapter(arrayAdapter)
    }

    private fun loadBabTime() {
        adapter = BabTimeListAdapter()
        adapter.addItem(PresenterBabTime("첫 끼", "-", "-"))
        list_view_bab_time.adapter = adapter
    }

    private fun addBabTime() {
        btn_add_bab_time.setOnClickListener {
            val size = adapter.count
            var bab = "두 끼"
            if (size == 2) bab = "세 끼"
            if (size == 3) Toast.makeText(this, "세끼가 최대야", Toast.LENGTH_SHORT).show()
            else {
                adapter.addItem(PresenterBabTime(bab, "-", "-"))
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun clickBabTime() {
        list_view_bab_time.setOnItemClickListener { parent, view, p, id ->
            val item = parent.getItemAtPosition(p) as PresenterBabTime
            val timePicker = MaterialTimePicker.Builder()
                .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .build()
            timePicker.show(supportFragmentManager, "createGroup")
            timePicker.addOnPositiveButtonClickListener {
                var meridiem = "오전"
                var hour: Int = timePicker.hour
                val minute = timePicker.minute
                if (hour > 12) {
                    hour -= 12
                    meridiem = "오후"
                }
                val strHour = if (hour.toString().length == 1) "0$hour" else hour.toString()
                val strMinute = if (minute.toString().length == 1) "0$minute" else minute.toString()
                adapter.setItem(p, item.bab, meridiem, "${strHour}:${strMinute}")
                adapter.notifyDataSetChanged()
            }
        }
    }
}