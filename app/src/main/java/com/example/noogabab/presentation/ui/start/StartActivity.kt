package com.example.noogabab.presentation.ui.start

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.start.createGroup.CreateGroupActivity
import com.example.noogabab.presentation.ui.start.enterGroup.EnterGroupActivity
import com.example.noogabab.util.DynamicTextWatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_create_group.*
import kotlinx.android.synthetic.main.activity_start.*

@AndroidEntryPoint
class StartActivity : AppCompatActivity(), View.OnClickListener {
    private val viewModel: StartViewModel by viewModels()
    private val textWatcher = DynamicTextWatcher(
        onChanged = { _, _, _, _ ->
            viewModel.updateKey(edit_key.text.toString())
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        loader()
        observe()
    }

    private fun loader() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        )
        edit_key.addTextChangedListener(textWatcher)
        btn_start.setOnClickListener(this)
        btn_create_group.setOnClickListener(this)
    }

    private fun observe() {
        viewModel.currentBtnState.observe(this, Observer {
            btn_start.isEnabled = it
            if (it) btn_start.setBackgroundColor(applicationContext.getColor(R.color.color_aa5900))
            else btn_start.setBackgroundColor(applicationContext.getColor(R.color.color_e7d0b7))
        })
    }

    override fun onClick(view: View?) {
        when(view) {
            btn_start -> startActivity(Intent(this, EnterGroupActivity::class.java))
            btn_create_group -> startActivity(Intent(this, CreateGroupActivity::class.java))
        }
    }
}