package com.flesh.furballs.models.cat

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by aaronfleshner on 11/10/17.
 */
data class CatCategories(var category: List<CatCategory>?) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(CatCategory))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(category)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<CatCategories> {
        override fun createFromParcel(parcel: Parcel): CatCategories {
            return CatCategories(parcel)
        }

        override fun newArray(size: Int): Array<CatCategories?> {
            return arrayOfNulls(size)
        }
    }
}