package com.flesh.furballs.di.modules

import android.content.Context
import com.flesh.furballs.R
import com.flesh.furballs.web.parsing.WebResponseInterfaceIstanceCreator
import com.flesh.furballs.web.parsing.XMLGsonConverterFactory
import com.flesh.furballs.models.dog.DogImageResponse
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


/**
 * Provides the Retrofit for the app.
 * Created by aaronfleshner on 8/2/17.
 */
@Module
class RetrofitModule(private var context: Context) {

    //Creates logging interceptor for when its just not working write.
//    val interceptor = HttpLoggingInterceptor()
//    interceptor.level = HttpLoggingInterceptor.Level.BODY
//    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Singleton
    @Provides
    @Named(value = "dogRetrofit")
    fun providesDogClient(): Retrofit {
        val gson = GsonBuilder().registerTypeAdapter(DogImageResponse::class.java, WebResponseInterfaceIstanceCreator(context)).create()

        return Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_dog_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

    }


    @Singleton
    @Provides
    @Named(value = "catRetrofit")
    fun providesCatClient(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_cat_url))
                .addConverterFactory(XMLGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }


}