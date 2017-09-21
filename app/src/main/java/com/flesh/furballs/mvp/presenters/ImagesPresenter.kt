package com.flesh.furballs.mvp.presenters

import com.flesh.furballs.web.WebRestAPI
import com.flesh.furballs.mvp.views.ImagesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by aaronfleshner on 8/7/17.
 */
class ImagesPresenter(var provider : WebRestAPI , private var view : ImagesView) {


    fun loadImages(breed : String = ""){
        view.showLoading()
        view.hideEmpty()
        provider.getDogImages(breed)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    view.hideEmpty()
                    view.hideLoading()
                    view.dataLoaded(result)
                },{error ->
                    view.hideEmpty()
                    view.hideLoading()
                    view.ErrorLoaded(error)
                })
    }


    fun loadImages(breed : String = "",subBreed: String = ""){
        view.showLoading()
        provider.getDogImages(breed,subBreed)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    view.hideEmpty()
                    view.hideLoading()
                    view.dataLoaded(result)
                },{error ->
                    view.hideEmpty()
                    view.hideLoading()
                    view.ErrorLoaded(error)
                })
    }





}
