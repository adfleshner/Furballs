package com.flesh.furballs.di.modules

import android.content.Context
import com.flesh.furballs.R
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Provides the Retrofit for the app.
 * Created by aaronfleshner on 8/2/17.
 */
@Module
class RetrofitModule(private var context: Context) {

    @Singleton
    @Provides
    fun providesWebClient(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

}