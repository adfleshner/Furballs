package com.flesh.furballs.models.cat

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by aaronfleshner on 11/6/17.
 */
data class CatData(@SerializedName("images")var images:CatImages) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readParcelable<CatImages>(CatImages::class.java.classLoader))


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(images, flags)
    }

    override fun describeContents() = 0


    companion object CREATOR : Parcelable.Creator<CatData> {
        override fun createFromParcel(parcel: Parcel): CatData {
            return CatData(parcel)
        }

        override fun newArray(size: Int): Array<CatData?> {
            return arrayOfNulls(size)
        }
    }
}