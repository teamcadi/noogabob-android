package com.example.noogabab.presentation.ui.main.album

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.bumptech.glide.Glide
import com.example.noogabab.R
import com.example.noogabab.presentation.entity.PresenterAlbumImage
import kotlinx.android.synthetic.main.fragment_album.*

class AlbumFragment : Fragment(R.layout.fragment_album) {
    var items = ArrayList<PresenterAlbumImage>()
    var ids = arrayOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)
    var images = intArrayOf(
        R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5,
        R.drawable.image6, R.drawable.image7, R.drawable.image8, R.drawable.image9, R.drawable.image10,
        R.drawable.image11, R.drawable.image12, R.drawable.image13, R.drawable.image14, R.drawable.image15,
        R.drawable.image16, R.drawable.image17, R.drawable.image18, R.drawable.image19, R.drawable.image20,
        R.drawable.image21, R.drawable.image22, R.drawable.image23
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor = Color.WHITE;


        for (i in ids.indices) items.add(PresenterAlbumImage(ids[i], images[i]))
        var adapter = AlbumAdapter(items, requireContext())
        grid_album.adapter = adapter
//
//        grid_album.setOnItemClickListener { adapterView, view, i, l ->
//            var intent = Intent(activity, AlbumDetailActivity::class.java)
//            intent.putExtra("data", items[i])
//            startActivity(intent)
//        }
    }
}