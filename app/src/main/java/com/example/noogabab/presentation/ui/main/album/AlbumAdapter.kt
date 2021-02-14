package com.example.noogabab.presentation.ui.main.album

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.noogabab.R
import com.example.noogabab.presentation.entity.PresenterAlbumImage
import com.example.noogabab.presentation.ui.main.timeline.TimelineViewHolder
import kotlinx.android.synthetic.main.item_album.view.*

//class AlbumAdapter(private val items: ArrayList<PresenterAlbumImage>, private val context: Context): BaseAdapter() {
//    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//
//    override fun getCount() = items.size
//    override fun getItem(p: Int) = items[p]
//    override fun getItemId(p: Int) = p.toLong()
//    override fun getView(p: Int, view: View?, parent: ViewGroup?): View {
//        var current = view
//        if (current == null) {
//            current = layoutInflater.inflate(R.layout.item_album, parent, false)
//        }
//        var imageView = view?.findViewById<ImageView>(R.id.image_album)
//        if (imageView != null) {
//            Glide.with(context).load(items[p].image)
//                .centerCrop()
//                .placeholder(R.drawable.indeterminate_progress)
//                .error(R.drawable.ic_background_album)
//                .into(imageView)
//        }
//        return current!!
//    }
//
//}

class AlbumAdapter(private val items: ArrayList<PresenterAlbumImage>) : RecyclerView.Adapter<AlbumViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflate = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_album, parent, false)

        return AlbumViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

}

class AlbumViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    fun bind(item: PresenterAlbumImage) {
        with(view) {
            Glide.with(context).load(item.image)
                .centerCrop()
                .placeholder(R.drawable.indeterminate_progress)
                .error(R.drawable.ic_background_album)
                .into(image_album)
        }
    }
}