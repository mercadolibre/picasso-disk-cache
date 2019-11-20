package com.mercadolibre.android.picassodiskcache.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
                "https://mobile.mercadolibre.com/remote_resources/image/buflo_payment_card_visa-debito-white?density=xhdpi&locale=en"

            )
            .into(sampleImage)

        sampleImage2
            .loadImage("https://i.pinimg.com/originals/a7/87/88/a78788d285a647701307d615d5d2a08b.jpg")
    }
}