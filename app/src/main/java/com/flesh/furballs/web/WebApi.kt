package com.flesh.furballs.web

import com.flesh.furballs.models.WebResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by aaronfleshner on 7/31/17.
 * API interface that Retrofit Uses
 *
 */
interface WebApi {

    @GET("breed/{breed}/images")
    fun getDogImages(@Path("breed")breed : String) : Observable<WebResponse>

    @GET("breed/{breed}/{sbreed}/images")
    fun getDogImages(@Path("breed")breed : String,@Path("sbreed")sbreed : String) : Observable<WebResponse>

}