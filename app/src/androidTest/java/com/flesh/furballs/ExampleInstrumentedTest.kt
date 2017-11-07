package com.flesh.furballs

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.flesh.furballs.models.AnimalType
import com.flesh.furballs.models.dog.DogImageResponse
import com.flesh.furballs.models.shared.IWebImageResponse
import com.flesh.furballs.web.BaseWebApi
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
    fun loadsDataForBreedAndSBreed() {
        val breed = "LEO"
        testApi.getAnimalImages(breed,"", AnimalType.DOG)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    assertEquals(result, DogImageResponse("DOG and LEO", listOf("image DOG and LEO 1", "image DOG and LEO 2")))
                },{error ->

                })
    }

    @Test
    fun loadAllOfTheBreeds(){
        testApi.getCategories(AnimalType.DOG)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    assertEquals(result.status,"success")
                    assertEquals(result.response.size,4)
                },{error ->

                })
    }



    class MockApiForTests : BaseWebApi {
        override fun getAnimalImages(animal_filter: String?, sub_id: String, type: AnimalType): Observable<IWebImageResponse> {
            return Observable.just(DogImageResponse(animal_filter ?: "", listOf("image $animal_filter 1", "image $animal_filter 2")))

        }

        override fun getCategories(type: AnimalType): Observable<IWebImageResponse> {
            return Observable.just(DogImageResponse("success", listOf("All", "sorts", "of", "breeds")))

        }
    }

}
