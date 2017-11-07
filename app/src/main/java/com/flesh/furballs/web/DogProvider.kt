package com.flesh.furballs.web

import com.flesh.furballs.models.dog.DogImageResponse
import com.flesh.furballs.models.dog.DogQueryResponse
import com.flesh.furballs.models.shared.IWebImageResponse
import io.reactivex.Observable

/**
 * Creates a Restful area for the DogApi to be called from.
 * Created by aaronfleshner on 8/2/17.
 */
open class DogProvider(protected var dogApi: DogApi) : DogApi {

    //DOG API
    override fun getAllDogBreeds(): Observable<DogQueryResponse>{
        return dogApi.getAllDogBreeds()
    }

    override fun getDogImages(breed: String): Observable<DogImageResponse> {
        return dogApi.getDogImages(breed)
    }

    override fun getDogImages(breed: String, sbreed: String): Observable<DogImageResponse> {
        return dogApi.getDogImages(breed,sbreed)
    }

}