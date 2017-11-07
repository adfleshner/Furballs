package com.flesh.furballs.mvp.presenters

import com.flesh.furballs.models.AnimalType
import com.flesh.furballs.mvp.views.ImagesView
import com.flesh.furballs.web.BaseWebProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by aaronfleshner on 8/7/17.
 * The presenter for the Images fragment
 */
class ImagesPresenter(private var provider: BaseWebProvider, private var view: ImagesView) {

    fun loadFilters(type: AnimalType) {
            provider.getCategories(type)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        view.loadQueries(result)
                    }, { error ->
                        view.hideSearch()
                        view.ErrorLoaded(error)
                    })

    }


    fun loadImages(breed: String = "",category :String? = null,type: AnimalType = AnimalType.DOG) {
        view.showLoading()
        view.hideEmpty()
        val filter = if(type== AnimalType.DOG){breed}else if(type== AnimalType.CAT){category}else{""}
        provider.getAnimalImages(filter,"",type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    view.hideEmpty()
                    view.hideLoading()
                    view.dataLoaded(result)
                }, { error ->
                    view.hideEmpty()
                    view.hideLoading()
                    view.ErrorLoaded(error)
                })
    }


}
