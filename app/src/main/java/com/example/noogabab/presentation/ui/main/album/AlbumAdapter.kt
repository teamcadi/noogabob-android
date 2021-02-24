package com.example.noogabab.presentation.ui.main.album

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.noogabab.R
import com.example.noogabab.presentation.entity.PresenterAlbumImage
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumAdapter(private val items: ArrayList<PresenterAlbumImage>) : RecyclerView.Adapter<AlbumViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflate = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_album, parent, false)

        return AlbumViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(items[position])
        holder.view.setOnClickListener {
            val i = Intent(holder.view.context, AlbumDetailActivity::class.java)
            i.putExtra("image", items[position].image)
            i.putExtra("id", items[position].id)
            ContextCompat.startActivity(holder.view.context, i, null)
        }
    }

    override fun getItemCount() = items.size

    fun addItem(item: PresenterAlbumImage) {
        items.add(item)
        notifyDataSetChanged()
    }
}

class AlbumViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    fun bind(item: PresenterAlbumImage) {
        with(view) {
            Glide.with(context).load(item.image)
                .centerCrop()
                .error(R.drawable.ic_background_album)
                .into(image_album)
        }
    }
}