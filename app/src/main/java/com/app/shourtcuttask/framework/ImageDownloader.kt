package com.app.shourtcuttask.framework

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.Nullable
import com.app.shourtcuttask.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ImageDownloader @Inject constructor(@ApplicationContext private val context: Context) {

    fun downloadImage(
        url: String,
        onSuccess: (bitMap: Bitmap) -> Unit,
        onError: () -> Unit
    ) {
        Glide
            .with(context)
            .asBitmap()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .load(url)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    @Nullable transition: Transition<in Bitmap?>?
                ) {
                    onSuccess(resource)
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    onError()
                }
            })
    }
}