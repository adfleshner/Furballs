package com.flesh.furballs

import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import android.os.Parcel
import com.flesh.furballs.models.dog.DogImageResponse
import org.junit.Assert.*

/**
 * Created by aaronfleshner on 8/2/17.
 */
@RunWith(AndroidJUnit4::class)
class ParcelableInstrumentedTests {

    @Test
    fun testWebResponseParcelable() {
        val response = DogImageResponse("", listOf())
        val parcel = Parcel.obtain()
        response.writeToParcel(parcel, 0)
        parcel.setDataPosition(0)
        val parceledResponse = DogImageResponse.CREATOR.createFromParcel(parcel)
        assertEquals(response,parceledResponse)
    }

}