package com.flesh.furballs.web

import com.flesh.furballs.models.cat.CatResponse
import com.flesh.furballs.models.shared.IWebQueryResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by aaronfleshner on 11/5/17.
 */
interface CatApi {

    enum class Size(val size:String) {
        SMALL("small"), MED("med"), FULL("full")
    }

    enum class Type(type:String) {
        JPG("jpg"), PNG("png"), GIF("gif")
    }

    /**
     * size = small,med,full
     */
    @GET(value = "images/get")
    fun getCatApiGet(
            @Query("api_key") apikey: String,
            @Query("format") format: String = "xml",
            @Query("results_per_page") count: Int = 100,
            @Query("image_id") image_id: String?=null,
            @Query("type") type: Type? = null,
            @Query("category") category: String?=null,
            @Query("size") size: Size? = null,
            @Query("sub_id") sub_id: String?=null): Observable<CatResponse>


    @GET(value = "images/vote")
    fun catVote(@Query("api_key") apikey:String,
                @Query("image_id") image_id:String,
                @Query("score") score:Int,
                @Query("sub_id") sub_id:String)


    @GET(value = "images/vote")
    fun catgetVote(@Query("api_key") apikey:String,
                   @Query("sub_id") sub_id:String)

    enum class action(val action:String){
        ADD("add"),REMOVE("remove")
    }

    @GET(value = "images/favorite")
    fun catFavorite(
            @Query("api_key") apikey:String,
            @Query("image_id") image_id:String,
            @Query("action") action:Int,
            @Query("sub_id") sub_id:String
    )


    @GET(value= "images/getfavorites")
    fun catGetFavorites(
            @Query("api_key") apikey:String,
            @Query("sub_id") sub_id:String
    )

    @GET(value= "images/report")
    fun reportThatCat(
            @Query("api_key") apikey:String,
            @Query("image_id") image_id:String,
            @Query("sub_id") sub_id:String,
            @Query("reason") reason:String
    )

    @GET(value= "categories/list")
    fun getCatCategories(): Observable<IWebQueryResponse>

    @GET(value = "stats/getOverview")
    fun getCatOverview(@Query("api_key") apikey:String)
}