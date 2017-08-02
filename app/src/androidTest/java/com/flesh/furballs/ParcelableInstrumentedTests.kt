package com.flesh.furballs

import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import android.os.Parcel
import com.flesh.furballs.models.WebResponse
import org.junit.Assert.*

/**
 * Created by aaronfleshner on 8/2/17.
 */
@RunWith(AndroidJUnit4::class)
class ParcelableInstrumentedTests {

    @Test
    fun testWebResponseParcelable() {
        val response = WebResponse("", listOf())
        val parcel = Parcel.obtain()
        response.writeToParcel(parcel, 0)
        parcel.setDataPosition(0)
        val parceledResponse = WebResponse.CREATOR.createFromParcel(parcel)
        assertEquals(response,parceledResponse)
    }

}