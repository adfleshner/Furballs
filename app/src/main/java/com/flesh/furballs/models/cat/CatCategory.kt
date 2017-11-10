package com.flesh.furballs.models.cat

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by aaronfleshner on 11/10/17.
 */
data class CatCategory(var id:String,var name:String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString())
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents() = 0


    companion object CREATOR : Parcelable.Creator<CatCategory> {
        override fun createFromParcel(parcel: Parcel): CatCategory {
            return CatCategory(parcel)
        }

        override fun newArray(size: Int): Array<CatCategory?> {
            return arrayOfNulls(size)
        }
    }
}