package com.flesh.furballs.web

import com.flesh.furballs.models.AnimalType
import com.flesh.furballs.models.shared.IWebImageResponse
import com.flesh.furballs.models.shared.IWebQueryResponse
import io.reactivex.Observable

/**
 * Created by aaronfleshner on 11/5/17.
 */
interface BaseWebApi {
    fun getCategories(type: AnimalType): Observable<IWebQueryResponse>
    fun getAnimalImages(animal_filter: String?, sub_id: String,type: AnimalType): Observable<IWebImageResponse>
}
