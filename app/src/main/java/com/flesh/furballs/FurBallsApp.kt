package com.flesh.furballs

import android.app.Application
import com.flesh.furballs.di.AppComponent
import com.flesh.furballs.di.DaggerAppComponent
import com.flesh.furballs.di.modules.AppModule
import com.flesh.furballs.di.modules.GlideModule
import com.flesh.furballs.di.modules.RestApiModule
import com.flesh.furballs.di.modules.RetrofitModule

/**
 * Furballs Custom Application Layer
 * Created by aaronfleshner on 8/1/17.
 */
class FurBallsApp : Application() {

    companion object {
        lateinit var appComponent : AppComponent
    }


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .restApiModule(RestApiModule())
                .retrofitModule(RetrofitModule(this.applicationContext))
                .appModule(AppModule(this))
                .glideModule(GlideModule(this.applicationContext))
                .build()
    }
}