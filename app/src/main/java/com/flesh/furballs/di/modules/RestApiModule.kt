package com.flesh.furballs.di.modules

import com.flesh.furballs.web.WebApi
import com.flesh.furballs.web.WebRestAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Provides the WebApi And WebRestApi
 * Created by aaronfleshner on 8/2/17.
 */
@Module
class RestApiModule {

    @Provides
    @Singleton
    fun provideWebRestAPI(webApi: WebApi): WebRestAPI = WebRestAPI(webApi)

    @Provides
    @Singleton
    fun provideWebApi(retrofit: Retrofit): WebApi = retrofit.create(WebApi::class.java)

}