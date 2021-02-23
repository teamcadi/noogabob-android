package com.example.noogabab.presentation.ui.start

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.example.noogabab.R
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.presentation.ui.start.createGroup.CreateGroupActivity
import com.example.noogabab.presentation.ui.start.enterGroup.EnterGroupActivity
import com.example.noogabab.util.DynamicTextWatcher
import com.example.noogabab.util.SharedDog
import com.example.noogabab.util.SharedGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_start.*
import kotlin.system.exitProcess

@AndroidEntryPoint
class StartActivity : AppCompatActivity(), View.OnClickListener {
    private val startViewModel: StartViewModel by viewModels<StartViewModel>()
    private var backPressedTime: Long = 0
    private val textWatcher = DynamicTextWatcher(
        onChanged = { _, _, _, _ ->
            startViewModel.updateKey(edit_key.text.toString())
        }
    )

    override fun onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            ActivityCompat.finishAffinity(this);
            exitProcess(0)
        }
        Toast.makeText(this, R.string.toast_exit_back_pressed, Toast.LENGTH_SHORT).show()
        backPressedTime = System.currentTimeMillis()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        load()
        observe()
    }

    private fun load() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        )
        edit_key.addTextChangedListener(textWatcher)
        btn_start.setOnClickListener(this)
        btn_create_group.setOnClickListener(this)
    }

    private fun observe() {
        startViewModel.currentBtnState.observe(this, Observer {
            btn_start.isEnabled = it
            if (it) btn_start.setBackgroundColor(applicationContext.getColor(R.color.color_aa5900))
            else btn_start.setBackgroundColor(applicationContext.getColor(R.color.color_e7d0b7))
        })
    }

    private fun enterCreateUser() {
        startViewModel.getDog().observe(this, Observer { resultData ->
            when (resultData) {
                is ResultData.Loading -> {
                }
                is ResultData.Success -> {
                    resultData.data!!.dogData!!.apply {
                        val sharedDog = getSharedPreferences(SharedDog.NAME, Context.MODE_PRIVATE)
                        val sharedGroup = getSharedPreferences(SharedGroup.NAME, Context.MODE_PRIVATE)
                        val dogEditor = sharedDog.edit()
                        val groupEditor = sharedGroup.edit()
                        dogEditor.putInt(SharedDog.DOG_ID_KEY, dogId!!)
                        dogEditor.putString(SharedDog.DOG_NAME_KEY, name!!)
                        dogEditor.putInt(SharedDog.DOG_AGE_KEY, age!!)
                        dogEditor.putString(SharedDog.DOG_KIND_KEY, kind!!)
                        dogEditor.putString(
                            SharedDog.DOG_MEALS_KEY,
                            meals!!.joinToString(separator = ",") // 10:10:10,12:01:02,...
                        )
                        groupEditor.putString(SharedGroup.GROUP_UUID_KEY, startViewModel.getGroupKey())
                        dogEditor.apply()
                        groupEditor.apply()
                    }
                    startActivity(Intent(this, EnterGroupActivity::class.java))
                }
                is ResultData.Failed -> {
                    Toast.makeText(this, this.getString(R.string.toast_server_failed), Toast.LENGTH_SHORT)
                        .show()
                }
            }

        })
    }

    override fun onClick(view: View?) {
        when(view) {
            btn_start -> enterCreateUser()
            btn_create_group -> startActivity(Intent(this, CreateGroupActivity::class.java))
        }
    }

}