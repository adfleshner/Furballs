package com.flesh.furballs.di

import android.app.Activity
import android.support.v4.app.Fragment
import com.flesh.furballs.activities.MainActivity
import com.flesh.furballs.di.modules.AppModule
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
        RetrofitModule::class))
interface AppComponent {

        fun inject(imagesFragment: ImagesFragment)

}