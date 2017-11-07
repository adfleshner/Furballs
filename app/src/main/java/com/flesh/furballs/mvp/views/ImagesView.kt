package com.flesh.furballs.mvp.views

import com.flesh.furballs.models.shared.IWebImageResponse
import com.flesh.furballs.models.shared.IWebQueryResponse

/**
 * Created by aaronfleshner on 8/7/17.
 *
 */
interface ImagesView : BaseView<IWebImageResponse, Throwable>{
    fun hideSearch()
    fun loadQueries(result: IWebQueryResponse)
    fun loadImages(result: IWebImageResponse)
}


