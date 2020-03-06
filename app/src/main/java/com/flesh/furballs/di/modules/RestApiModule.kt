package com.flesh.furballs.di.modules

import android.content.Context
import com.flesh.furballs.web.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Provides the DogApi And WebRestApi
 * Created by aaronfleshner on 8/2/17.
 */
@Module
class RestApiModule {

    @Provides
    @Singleton
    @Named("api")
    fun provideBaseWebRestApi(context: Context, dogApi: DogApi, catApi: CatApi): BaseWebProvider = BaseWebProvider(context = context, dogApi = dogApi,catApi = catApi)

    @Provides
    @Singleton
    fun provideDogApi(@Named("dogRetrofit")dogRetrofit: Retrofit): DogApi = dogRetrofit.create(DogApi::class.java)

    @Provides
    @Singleton
    fun provideCatApi(@Named("catRetrofit")catRetrofit: Retrofit):CatApi = catRetrofit.create(CatApi::class.java)

}