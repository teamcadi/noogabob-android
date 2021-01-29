package com.example.noogabab.presentation.ui.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.start.createGroup.CreateGroupActivity
import com.example.noogabab.presentation.ui.start.enterGroup.EnterGroupActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_start.*

@AndroidEntryPoint
class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        )

        btn_start.setOnClickListener {
            startActivity(Intent(this, EnterGroupActivity::class.java))
        }

        btn_create_group.setOnClickListener {
            startActivity(Intent(this, CreateGroupActivity::class.java))
        }
    }
}