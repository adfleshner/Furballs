package com.flesh.furballs.mvp.views

/**
 * Created by aaronfleshner on 8/7/17.
 */
interface BaseView<in T, in Error : Throwable> {


    fun showLoading()
    fun hideLoading()
    fun dataLoaded(data:T)
    fun ErrorLoaded(error:Error)
    fun showEmpty()
    fun hideEmpty()

}