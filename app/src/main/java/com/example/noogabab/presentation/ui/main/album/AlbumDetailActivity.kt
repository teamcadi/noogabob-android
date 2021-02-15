package com.example.noogabab.presentation.ui.main.album

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.noogabab.R
import com.example.noogabab.presentation.entity.PresenterAlbumImage
import kotlinx.android.synthetic.main.activity_album_detail.*
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        val image = intent.getStringExtra("image")
        val id = intent.getLongExtra("id", -1)

        Glide.with(this).load(image)
            .placeholder(R.drawable.indeterminate_progress)
            .error(R.drawable.ic_background_album)
            .into(image_album_detal)

        btn_back_detail_album.setOnClickListener {
            finish()
        }
    }
}