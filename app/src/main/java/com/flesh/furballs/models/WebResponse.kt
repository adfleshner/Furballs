package com.flesh.furballs.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by aaronfleshner on 7/31/17.
 * Simple data class for a web data from the dog api
 */
data class WebResponse(var status : String, var message: List<String>) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.createStringArrayList())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(status)
        parcel.writeStringList(message)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<WebResponse> {
        override fun createFromParcel(parcel: Parcel): WebResponse {
            return WebResponse(parcel)
        }

        override fun newArray(size: Int): Array<WebResponse?> {
            return arrayOfNulls(size)
        }
    }
}