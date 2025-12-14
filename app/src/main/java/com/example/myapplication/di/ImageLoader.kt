package com.example.myapplication.di

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageLoader @Inject constructor() {
    
    fun loadImage(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(imageView)
    }
}
