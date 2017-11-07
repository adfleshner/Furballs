package com.flesh.furballs.models.cat

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by aaronfleshner on 11/6/17.
 */
data class Response(@SerializedName("data")var data:CatData) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readParcelable<CatData>(CatData::class.java.classLoader))


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(data, flags)
    }

    override fun describeContents()= 0


    companion object CREATOR : Parcelable.Creator<Response> {
        override fun createFromParcel(parcel: Parcel): Response {
            return Response(parcel)
        }

        override fun newArray(size: Int): Array<Response?> {
            return arrayOfNulls(size)
        }
    }
}