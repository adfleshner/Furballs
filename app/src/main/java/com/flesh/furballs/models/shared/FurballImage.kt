package com.flesh.furballs.models.shared

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by aaronfleshner on 11/7/17.
 */
data class FurballImage(val url:String,val url_source:String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()?:"",
            parcel.readString()?:"")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(url_source)
    }

    override fun describeContents()= 0

    companion object CREATOR : Parcelable.Creator<FurballImage> {
        override fun createFromParcel(parcel: Parcel): FurballImage {
            return FurballImage(parcel)
        }

        override fun newArray(size: Int): Array<FurballImage?> {
            return arrayOfNulls(size)
        }
    }
}