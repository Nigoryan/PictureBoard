package com.example.pictureboardapp.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.pictureboardapp.R
import com.example.pictureboardapp.entity.ImageResponse
import kotlinx.android.synthetic.main.recycler_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call


class RecyclerAdapter(private val callback: OnItemClick) : RecyclerView.Adapter<RecyclerAdapter.MainViewHolder>() {

    val list =  mutableListOf<Call<ImageResponse>>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MainViewHolder(view)
    }
    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            callback.OnItemClicked(holder.adapterPosition)
        }
    }


    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.ivItem
        private val coroutineScope = CoroutineScope(Dispatchers.IO)
        val urlLiveData = MutableLiveData<Drawable>()
        fun bind(item: Call<ImageResponse>) {

            Glide
                .with(itemView.context)
                .asBitmap()
                .load(item.request().url.toUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.circleCropTransform())
                .into(object : CustomTarget<Bitmap>(){
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        icon.setImageBitmap(resource)
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {
                        // this is called when imageView is cleared on lifecycle call or for
                        // some other reason.
                        // if you are referencing the bitmap somewhere else too other than this imageView
                        // clear it here as you can no longer have the bitmap
                    }
                })

        }
    }

    interface OnItemClick {
        fun OnItemClicked(position: Int)
    }
}