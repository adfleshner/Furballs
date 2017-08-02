package com.flesh.furballs.web

import com.flesh.furballs.models.WebResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Creates a Restful area for the WebApi to be called from.
 * Created by aaronfleshner on 8/2/17.
 */
class WebRestAPI @Inject constructor(private var webApi: WebApi):WebApi {

    override fun getDogImages(breed: String): Observable<WebResponse> {
        return webApi.getDogImages(breed)
    }

    override fun getDogImages(breed: String, sbreed: String): Observable<WebResponse> {
        return webApi.getDogImages(breed,sbreed)
    }

}