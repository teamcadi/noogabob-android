package com.example.noogabab.presentation.ui.start

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.start.createGroup.CreateGroupActivity
import com.example.noogabab.presentation.ui.start.enterGroup.EnterGroupActivity
import com.example.noogabab.util.DynamicTextWatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_create_group.*
import kotlinx.android.synthetic.main.activity_start.*

@AndroidEntryPoint
class StartActivity : AppCompatActivity() {
    private val textWatcher = DynamicTextWatcher(
        onChanged = { _, _, _, _ ->
            if (edit_key.text.toString() == "") {
                btn_start.isEnabled = false
                btn_start.setBackgroundColor(applicationContext.resources.getColor(R.color.color_e7d0b7))
            } else {
                btn_start.isEnabled = true
                btn_start.setBackgroundColor(applicationContext.resources.getColor(R.color.color_aa5900))
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        )

        edit_key.addTextChangedListener(textWatcher)

        btn_start.setOnClickListener {
            startActivity(Intent(this, EnterGroupActivity::class.java))
        }

        btn_create_group.setOnClickListener {
            startActivity(Intent(this, CreateGroupActivity::class.java))
        }
    }
}