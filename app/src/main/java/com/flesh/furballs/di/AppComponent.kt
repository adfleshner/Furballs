package com.flesh.furballs.di

import com.flesh.furballs.activities.ImageActivity
import com.flesh.furballs.di.modules.AppModule
import com.flesh.furballs.di.modules.GlideModule
import com.flesh.furballs.di.modules.RestApiModule
import com.flesh.furballs.di.modules.RetrofitModule
import com.flesh.furballs.fragments.ImagesFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Injects all of the modules into the Fragment
 * Created by aaronfleshner on 8/1/17.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        RestApiModule::class,
        RetrofitModule::class,
        GlideModule::class))
interface AppComponent {

        fun inject(imagesFragment: ImagesFragment)
        fun inject(imageActivity: ImageActivity)

}