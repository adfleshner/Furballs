package com.flesh.furballs

import android.content.Context
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by aaronfleshner on 7/31/17.
 */
class WebService(var context: Context) {

    val serviceApi : WebApi

    init{
        var retrofit = Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        serviceApi = retrofit.create(WebApi::class.java)
    }

    fun getImages(breed:String):Observable<WebResponse>{
        return serviceApi.getDogImages(breed)
    }

}