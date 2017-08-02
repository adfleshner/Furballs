package com.flesh.furballs.di.modules

import android.content.Context
import com.flesh.furballs.FurBallsApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton



/**
 * Module to get the application context from.
 * Created by aaronfleshner on 8/1/17.
 */
@Module
class AppModule(var app: FurBallsApp) {

    @Provides
    @Singleton
    fun getContext(): Context = app.baseContext


    @Provides
    @Singleton
    fun provideApplication(): FurBallsApp = app


}