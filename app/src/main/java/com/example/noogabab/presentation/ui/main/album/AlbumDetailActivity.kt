package com.example.noogabab.presentation.ui.main.album

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.noogabab.R
import com.example.noogabab.util.SharedProfile
import kotlinx.android.synthetic.main.activity_album_detail.*
import kotlin.properties.Delegates

class AlbumDetailActivity : AppCompatActivity() {
    lateinit var image: String
    var id by Delegates.notNull<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        image = intent.getStringExtra("image")!!
        id = intent.getLongExtra("id", -1)

        Glide.with(this).load(image).into(image_album_detal)

        setSupportActionBar(toolbar_detail_album)
        btn_back_detail_album.setOnClickListener {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.select_detail_album, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.select_detail_album_delete -> {}
            R.id.select_detail_album_profile -> updateProfile()
        }
        return true
    }

    private fun updateProfile() {
        val shared = getSharedPreferences(SharedProfile.NAME, Context.MODE_PRIVATE)
        val editor = shared.edit()
        editor.putString(SharedProfile.IMAGE_KEY, image)
        editor.apply()
        Toast.makeText(this, "프로필 사진이 변경되었습니다.", Toast.LENGTH_SHORT).show()
    }
}