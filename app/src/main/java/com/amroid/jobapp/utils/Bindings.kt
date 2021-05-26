package com.amroid.jobapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.amroid.jobapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("profileImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl).apply(RequestOptions().circleCrop())
        .into(view)
}
@BindingAdapter("isFavourite")

 fun setisFavourite(view: ImageView,isFavourite:Boolean ) {
    view.setImageResource(
        if (isFavourite) {
            R.drawable.ic_fav_fill
        } else {
            R.drawable.ic_fav
        }
    )
}