package com.flesh.furballs

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.web.sugar.Web
import android.support.test.runner.AndroidJUnit4
import com.flesh.furballs.models.WebResponse
import com.flesh.furballs.mvp.presenters.ImagesPresenter
import com.flesh.furballs.mvp.views.BaseView
import com.flesh.furballs.web.WebApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    lateinit var testApi : MockApiForTests
    @Before
    fun setup(){
        testApi = MockApiForTests()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.flesh.furballs", appContext.packageName)
    }

    @Test
    fun loadsDataForBreed() {
        val breed = "DOG"
        testApi.getDogImages(breed)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    assertEquals(result,WebResponse("DOG", listOf("image DOG 1","image DOG 2")))
                },{error ->

                })
    }

    @Test
    fun loadsDataForBreedAndSBreed() {
        val breed = "DOG"
        val sBreed = "LEO"
        testApi.getDogImages(breed,sBreed)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    assertEquals(result,WebResponse("DOG and LEO", listOf("image DOG and LEO 1","image DOG and LEO 2")))
                },{error ->

                })
    }



    class MockApiForTests : WebApi{
        override fun getDogImages(breed: String): Observable<WebResponse> {
            return Observable.just(WebResponse(breed, listOf("image $breed 1","image $breed 2")))
        }

        override fun getDogImages(breed: String, sbreed: String): Observable<WebResponse> {
            return Observable.just(WebResponse("$breed and $sbreed", listOf("image $breed and $sbreed 1","image $breed and $sbreed 2")))
        }
    }

}
