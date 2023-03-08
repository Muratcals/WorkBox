package com.muratcalis.workbox.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.muratcalis.workbox.R

fun ImageView.downloadImage(resimUrl:String,context: Context){
    val options=RequestOptions.placeholderOf(progress(context))
    Glide.with(context).setDefaultRequestOptions(options).load(resimUrl).into(this)
}

fun progress(context:Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        centerRadius=40F
        strokeWidth=8F
        backgroundColor= R.color.beyaz
        start()
    }
}