package com.flesh.furballs.di.modules

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.flesh.furballs.R
import com.flesh.furballs.glide.GlideApp
import com.flesh.furballs.glide.GlideRequest
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton



/**
 * Created by aaronfleshner on 11/9/17.
 */
@Module
class GlideModule(private val context: Context){

    private val placeholder = R.mipmap.ic_launcher_foreground
    private val error = android.R.drawable.ic_menu_close_clear_cancel


    @Provides
    @Named("gif")
    fun providesGifGlide():GlideRequest<GifDrawable>{
        return GlideApp
                .with(context)
                .asGif()
                .placeholder(placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(error)
    }


    @Provides
    @Named("pic")
    fun providesGlide():GlideRequest<Drawable>{
        return GlideApp
                .with(context)
                .asDrawable()
                .placeholder(placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(error)
    }

}