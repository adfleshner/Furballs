package com.flesh.furballs.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.flesh.furballs.R
import com.flesh.furballs.toast
import com.flesh.furballs.web.parsing.XMLGsonConverterFactory
import com.flesh.furballs.web.CatApi
import com.flesh.furballs.web.CatProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class MainActivity : AppCompatActivity() , View.OnClickListener {
    companion object {
       @JvmStatic val TYPE = "${MainActivity::class.java} Animal Type"
    }


    override fun onClick(p0: View?) {
        val intent = Intent(this@MainActivity, ImagesActivity::class.java)
        when (p0?.id) {
            R.id.btn_dog -> {
                intent.putExtra(TYPE,"Dog")
                startActivity(intent)
            }
            R.id.btn_cat ->{
                intent.putExtra(TYPE,"Cat")
                startActivity(intent)
            }
            else -> toast("Choose Either Cat or Dog")
        }


    }

//    .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
//    Persister(AnnotationStrategy() // important part!
//    )))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_cat.setOnClickListener(this)
        btn_dog.setOnClickListener(this)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        var retro = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(XMLGsonConverterFactory.create())
                .baseUrl(getString(R.string.base_cat_url))
                .build()
        var cat = CatProvider(retro.create(CatApi::class.java))
        cat.shouldWork()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    Log.e("MAIN","Result = ${result.response}")
                }, { error ->
                    Log.e("MAIN",error.message)
                })
    }

}





