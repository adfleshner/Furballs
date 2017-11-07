package com.flesh.furballs.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.flesh.furballs.fragments.ImagesFragment
import com.flesh.furballs.R
import com.flesh.furballs.glide.GlideApp
import com.flesh.furballs.models.shared.FurballImage
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {

    val gif_ext = ".gif"

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        var furball : FurballImage = intent.getParcelableExtra(ImagesFragment.IMAGE_URL)
        val placeholder = R.mipmap.ic_launcher_foreground
        val error = android.R.drawable.ic_menu_close_clear_cancel
        val image = imageView
        val url = furball.url
        if (url.endsWith(gif_ext)) {
            GlideApp
                    .with(this@ImageActivity)
                    .asGif()
                    .load(url)
                    .placeholder(placeholder)
                    .error(error)
                    .into(image)
        } else {
            GlideApp
                    .with(this@ImageActivity)
                    .load(url)
                    .placeholder(placeholder)
                    .error(error)
                    .into(image)
        }
    }

}
