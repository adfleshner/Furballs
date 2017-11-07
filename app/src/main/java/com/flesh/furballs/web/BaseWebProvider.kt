package com.flesh.furballs.web

import android.content.Context
import com.flesh.furballs.models.AnimalType
import com.flesh.furballs.R
import com.flesh.furballs.models.shared.IWebImageResponse
import com.flesh.furballs.models.shared.IWebQueryResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aaronfleshner on 11/5/17.
 */
class BaseWebProvider @Inject constructor(var context: Context, var dogApi: DogApi, var catApi: CatApi) : BaseWebApi {

    val TAG = "[${BaseWebProvider::class.java}]"

    override fun getCategories(type: AnimalType): Observable<IWebQueryResponse>{
        return when(type){
            AnimalType.DOG-> { dogApi.getAllDogBreeds() as Observable<IWebQueryResponse> }
            AnimalType.CAT-> { catApi.getCatCategories() as Observable<IWebQueryResponse> }
        }
    }

    override fun getAnimalImages(animal_filter:String?, sub_id: String, type: AnimalType): Observable<IWebImageResponse> {
        return when(type){
            AnimalType.DOG-> dogApi.getDogImages(breed = animal_filter?:"")  as Observable<IWebImageResponse>
            AnimalType.CAT-> catApi.getCatApiGet(apikey = context.getString(R.string.cat_api_key),category = animal_filter,sub_id = sub_id)  as Observable<IWebImageResponse>
        }
    }

}