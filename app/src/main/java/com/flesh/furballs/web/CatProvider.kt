package com.flesh.furballs.web

import com.flesh.furballs.models.cat.CatResponse
import com.flesh.furballs.models.shared.IWebQueryResponse
import io.reactivex.Observable

/**
 * Created by aaronfleshner on 11/5/17.
 */
open class CatProvider(var webApi: CatApi) : CatApi {

    //CAT API
    override fun getCatApiGet(apikey: String, format: String, count: Int, image_id: String?,
                              type: CatApi.Type?, category: String?, size: CatApi.Size?,
                              sub_id: String?): Observable<CatResponse> {
        return webApi.getCatApiGet(apikey,format, count, image_id, type, category, size, sub_id)
    }



    override fun catVote(apikey: String, image_id: String, score: Int, sub_id: String) {
        return webApi.catVote(apikey,image_id, score, sub_id)
    }

    override fun catgetVote(apikey: String, sub_id: String) {
        return webApi.catgetVote(apikey, sub_id)
    }

    override fun catFavorite(apikey: String, image_id: String, action: Int, sub_id: String) {
        return webApi.catFavorite(apikey, image_id, action, sub_id)
    }

    override fun catGetFavorites(apikey: String, sub_id: String) {
        return webApi.catGetFavorites(apikey, sub_id)
    }

    override fun reportThatCat(apikey: String, image_id: String, sub_id: String, reason: String) {
        return webApi.reportThatCat(apikey, image_id, sub_id, reason)
    }

    override fun getCatCategories(): Observable<CatResponse> {
        return webApi.getCatCategories()
    }

    override fun getCatOverview(apikey: String) {
        return webApi.getCatOverview(apikey)
    }

}