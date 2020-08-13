package com.mercadolibre.android.picassodiskcache

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun <T : ImageView> T.loadImage(
    imageUrl: String? = null,
    placeHolder: Int? = null,
    saveInDisk: Boolean = true,
    onError: (() -> Unit)? = null,
    onDone: (() -> Unit)? = null
) {
    context?.run {
        val load = if (saveInDisk) {
            PicassoDiskLoader.get(this).load(imageUrl)
        } else {
            Picasso.get().load(imageUrl)
        }

        val callback = object : Callback {
            override fun onError(e: Exception?) {
                onError?.invoke()
            }

            override fun onSuccess() {
                onDone?.invoke()
            }
        }

        placeHolder?.let { load.placeholder(it) }
        load.into(this@loadImage, callback)
    }
}