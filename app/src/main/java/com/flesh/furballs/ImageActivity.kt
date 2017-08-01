package com.flesh.furballs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        var url = intent.getStringExtra(ImagesFragment.IMAGE_URL)
        Picasso.with(this).load(url).into(imageView)
    }
}
