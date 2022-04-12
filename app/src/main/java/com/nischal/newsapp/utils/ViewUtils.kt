package com.nischal.newsapp.utils

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.nischal.newsapp.R

/**
 * Method to set image from [imageUrl] to [ImageView]
 */
fun ImageView.setImageWithThumb(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        Glide.with(context.applicationContext)
            .load(
                ColorDrawable(
                    ContextCompat.getColor(
                        context.applicationContext,
                        R.color.colorByLine
                    )
                )
            )
            .centerCrop()
            .into(this)
    } else {
        Glide.with(context.applicationContext)
            .load(imageUrl)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(
                ColorDrawable(
                    ContextCompat.getColor(
                        context.applicationContext,
                        R.color.colorByLine
                    )
                )
            )
            .error(
                ColorDrawable(
                    ContextCompat.getColor(
                        context.applicationContext,
                        R.color.colorByLine
                    )
                )
            )
            .into(this)
    }
}

/**
 * Utility method to make view visible
 */
fun View.visible() {
    this.visibility = View.VISIBLE
}

/**
 * Utility method to make view gone
 */
fun View.gone() {
    this.visibility = View.GONE
}

/**
 * Utility method to make view invisible
 */
fun View.invisible() {
    this.visibility = View.INVISIBLE
}

/**
 * Utility method to show toast message
 */
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}