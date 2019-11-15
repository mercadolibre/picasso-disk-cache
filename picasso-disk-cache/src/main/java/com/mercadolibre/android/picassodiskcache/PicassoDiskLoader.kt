package com.mercadolibre.android.picassodiskcache

import android.annotation.SuppressLint
import android.content.Context
import com.mercadolibre.android.picassodiskcache.BuildConfig
import com.squareup.picasso.Picasso

object PicassoDiskLoader {
    @SuppressLint("StaticFieldLeak")
    @Volatile
    private var INSTANCE: Picasso? = null

    @JvmStatic
    fun get(context: Context): Picasso =
        INSTANCE ?: synchronized(this) {
            INSTANCE ?: Picasso.Builder(context.applicationContext)
                .downloader(PicassoDiskDownloader(context.applicationContext))
                .indicatorsEnabled(BuildConfig.DEBUG)
                .build().also { INSTANCE = it }
        }
}