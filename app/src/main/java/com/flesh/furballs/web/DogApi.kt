package com.flesh.furballs.web

import com.flesh.furballs.models.dog.DogImageResponse
import com.flesh.furballs.models.dog.DogQueryResponse
import com.flesh.furballs.models.shared.IWebImageResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by aaronfleshner on 7/31/17.
 * API interface that Retrofit Uses
 *
 */
interface DogApi {

    @GET(value = "breed/{breed}/images")
    fun getDogImages(@Path("breed") breed: String): Observable<DogImageResponse>

    @GET(value = "breed/{breed}/{sbreed}/images")
    fun getDogImages(@Path("breed") breed: String, @Path("sbreed") sbreed: String): Observable<DogImageResponse>

    @GET(value = "breeds/list")
    fun getAllDogBreeds(): Observable<DogQueryResponse>

}