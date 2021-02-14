package com.example.noogabab.presentation.ui.splash

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.start.StartActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        hideStatusBar()
        window.statusBarColor = Color.parseColor("#ffc176")
        setContentView(R.layout.activity_splash)
        startSplash()
    }

    private fun startSplash() {
        this.supportActionBar?.hide()
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            settingPermission()
        }
    }

    private fun settingPermission() {
        var permission = object : PermissionListener {
            override fun onPermissionGranted() {
                val intent = Intent(applicationContext, StartActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(applicationContext, "권한 거부", Toast.LENGTH_SHORT).show()
                ActivityCompat.finishAffinity(this@SplashActivity) // 권한 거부시 앱 종료
            }
        }
        TedPermission.with(this)
            .setPermissionListener(permission)
            .setRationaleMessage("카메라와 갤러리 접근 권한 필요합니다.")
            .setDeniedMessage("카메라 권한 요청 거부")
            .setPermissions(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA)
            .check()
    }
}