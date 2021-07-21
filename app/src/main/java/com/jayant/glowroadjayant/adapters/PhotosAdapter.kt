package com.jayant.glowroadjayant.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jayant.glowroadjayant.R
import com.jayant.glowroadjayant.models.PhotoModel

class PhotosAdapter(var context: Context, var photosList: ArrayList<PhotoModel>): RecyclerView.Adapter<PhotosViewHolder>() {
    private val TAG = PhotosAdapter::class.java.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val view: View = LayoutInflater.from(context).inflate(
            R.layout.layout_row_item,
            parent,
            false
        )

        val displayMetrics = context.resources.displayMetrics
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        return PhotosViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {

        Log.d(TAG, "onBindViewHolder title: ${photosList.get(position).title}")
        Log.d(TAG, "onBindViewHolder url: ${photosList.get(position).url_q}")

        holder.tv.text = photosList.get(position).title

        Glide.with(context)
            .load(photosList.get(position).url_q)
            .centerCrop()
            .into(holder.iv)
    }

    override fun getItemCount(): Int {
        return photosList.size
    }
}

class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val tv = itemView.findViewById<TextView>(R.id.tv)
    val iv = itemView.findViewById<ImageView>(R.id.iv)
}