package com.flesh.furballs.models.cat

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by aaronfleshner on 11/6/17.
 */
data class CatImages (@SerializedName("image")var image:List<CatImage>) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(CatImage))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(image)
    }

    override fun describeContents() = 0


    companion object CREATOR : Parcelable.Creator<CatImages> {
        override fun createFromParcel(parcel: Parcel): CatImages {
            return CatImages(parcel)
        }

        override fun newArray(size: Int): Array<CatImages?> {
            return arrayOfNulls(size)
        }
    }
}