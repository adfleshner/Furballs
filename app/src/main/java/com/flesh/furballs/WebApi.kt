package com.flesh.furballs

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by aaronfleshner on 7/31/17.
 */
interface WebApi {



    @GET("breed/{breed}/images")
    fun getDogImages(@Path("breed")breed : String) : Observable<WebResponse>



}