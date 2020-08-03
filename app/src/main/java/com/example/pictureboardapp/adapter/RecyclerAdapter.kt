package com.example.pictureboardapp.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.signature.ObjectKey
import com.example.pictureboardapp.R
import kotlinx.android.synthetic.main.recycler_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecyclerAdapter(private val callback: OnItemClick) : RecyclerView.Adapter<RecyclerAdapter.MainViewHolder>() {

    var item_count = 140
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MainViewHolder(view)
    }
    override fun getItemCount(): Int = item_count

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val url = "http://loremflickr.com/200/200/"
        holder.bind(url)
        holder.itemView.setOnClickListener {
            callback.OnItemClicked(holder.adapterPosition)
        }
    }


    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.ivItem
        private val coroutineScope = CoroutineScope(Dispatchers.IO)
        private val progress = itemView.loading
        fun bind(url: String) {
            coroutineScope.launch {
                icon.setImageBitmap(null)
                Glide
                    .with(itemView.context)
                    .asBitmap()
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true)
                    .signature(ObjectKey(System.currentTimeMillis()))
                    .load(url)
                    .into(object : CustomTarget<Bitmap>(){
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            icon.setImageBitmap(resource)
                            progress.visibility = View.GONE
                        }
                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })

            }
        }
    }

    interface OnItemClick {
        fun OnItemClicked(position: Int)
    }
}