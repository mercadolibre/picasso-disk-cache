package com.mercadolibre.android.picassodiskcache.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mercadolibre.android.picassodiskcache.PicassoDiskLoader
import com.mercadolibre.android.picassodiskcache.loadImage
import kotlinx.android.synthetic.main.activity_main.*

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PicassoDiskLoader
            .get(this)
            .load(
                "https://i.pinimg.com/originals/a7/87/88/a78788d285a647701307d615d5d2a08b.jpg"
            )
            .into(sampleImage)

        sampleImage2
            .loadImage("https://files.merca20.com/uploads/2018/03/Super-Mario-Nintendo-Bigstock.jpg")
    }
}
