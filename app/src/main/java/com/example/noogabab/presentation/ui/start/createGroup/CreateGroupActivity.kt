package com.example.noogabab.presentation.ui.start.createGroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.size
import androidx.lifecycle.Observer
import com.example.noogabab.R
import com.example.noogabab.presentation.dialog.CreateGroupDialog
import com.example.noogabab.util.DynamicTextWatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_create_group.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CreateGroupActivity : AppCompatActivity(), View.OnClickListener {
    private val countTime = arrayOf("첫 끼", "두 끼", "세 끼")
    private val viewModel: CreateGroupViewModel by viewModels()
    private val textWatcher = DynamicTextWatcher(
        onChanged = { _, _, _, _ ->
            viewModel.updateDogName(edit_dog_name.text.toString())
            viewModel.updateDogAge(edit_dog_age.text.toString())
            viewModel.updateDogKind(edit_dog_kind.text.toString())
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_group)
        loader()
        observe()
    }

    private fun loader() {
        val dogs = resources.getStringArray(R.array.dogs)
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dogs)
        edit_dog_kind.setAdapter(arrayAdapter)

        val firstBobTime = BobTimeView(
            applicationContext,
            supportFM = supportFragmentManager,
            viewModel = viewModel
        )
        firstBobTime.setRemoveInVisible()
        linear_bob_time.addView(firstBobTime)

        edit_dog_name.addTextChangedListener(textWatcher)
        edit_dog_age.addTextChangedListener(textWatcher)
        edit_dog_kind.addTextChangedListener(textWatcher)
        btn_get_key.setOnClickListener(this)
        btn_add_bob_time.setOnClickListener(this)
        linear_bob_time.setOnClickListener(this)
    }

    private fun observe() {
        viewModel.currentBtnState.observe(this, Observer {
            btn_get_key.isEnabled = it
            if (it) btn_get_key.setBackgroundColor(applicationContext.getColor(R.color.color_aa5900))
            else btn_get_key.setBackgroundColor(applicationContext.getColor(R.color.color_e7d0b7))
        })
    }

    override fun onClick(view: View?) {
        when (view) {
            btn_add_bob_time -> addBobTime()
            btn_get_key -> getKey()
        }
    }

    private fun addBobTime() {
        if (linear_bob_time.size < 3) {
            val index = linear_bob_time.size
            val bobTimeView = BobTimeView(
                applicationContext,
                supportFM = supportFragmentManager,
                viewModel = viewModel
            )
            bobTimeView.setCountBob(countTime[index])
            linear_bob_time.addView(bobTimeView)
        } else Toast.makeText(this, "그만줘요", Toast.LENGTH_SHORT).show()
    }

    private fun getKey() {
        val dialog = CreateGroupDialog(this) { finish() }
        CoroutineScope(Main).launch {
            dialog.show()
            delay(2000)
            // todo: 서버 호출
            dialog.setDialog(
                progress = false,
                btnClose = true,
                description = "발급 완료!",
                key = "12345678"
            )
        }
    }

}