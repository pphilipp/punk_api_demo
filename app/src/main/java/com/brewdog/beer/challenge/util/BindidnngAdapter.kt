package com.brewdog.beer.challenge.util

import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.circularreveal.cardview.CircularRevealCardView

@BindingAdapter("android:text")
fun setFloat(view: TextView, value: Double) {
    if (value.isNaN()){
        view.text = ""
    } else {
        view.text = value.toString()
    }
}

@BindingAdapter(
    value = ["app:imageUrl"]
)
fun imageUrl(view: ImageView, url: String?) {
    view.setImageDrawable(null)
    Glide.with(view)
        .asBitmap()
        .load(url)
        .apply(RequestOptions().override(view.width, view.height))
        .into(view)
}