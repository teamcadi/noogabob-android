package com.example.noogabab.presentation.ui.main.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.noogabab.R
import com.example.noogabab.presentation.entity.PresenterAlbumImage
import kotlinx.android.synthetic.main.activity_album_detail.*

class AlbumDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        val item: PresenterAlbumImage = intent.getSerializableExtra("data") as PresenterAlbumImage
    }
}