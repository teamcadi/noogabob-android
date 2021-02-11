package com.example.noogabab.presentation.ui.main.album

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.noogabab.R
import com.example.noogabab.presentation.entity.PresenterAlbumImage

class AlbumAdapter(private val items: ArrayList<PresenterAlbumImage>, private val context: Context): BaseAdapter() {
    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount() = items.size
    override fun getItem(p: Int) = items[p]
    override fun getItemId(p: Int) = p.toLong()
    override fun getView(p: Int, view: View?, parent: ViewGroup?): View {
        var current = view
        if (current == null) {
            current = layoutInflater.inflate(R.layout.item_album, parent, false)
        }
        var image = view?.findViewById<ImageView>(R.id.image_album)
        image?.setImageResource(items[p].image!!)

        return current!!
    }

}