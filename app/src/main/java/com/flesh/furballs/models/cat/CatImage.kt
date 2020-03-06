package com.flesh.furballs.models.cat

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by aaronfleshner on 11/6/17.
 */
data class CatImage(@SerializedName("id")var id:String,@SerializedName("url")var  url:String,@SerializedName("source_url") var source_url:String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()?:"",
            parcel.readString()?:"",
            parcel.readString()?:"") {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(url)
        parcel.writeString(source_url)
    }

    override fun describeContents()= 0


    companion object CREATOR : Parcelable.Creator<CatImage> {
        override fun createFromParcel(parcel: Parcel): CatImage {
            return CatImage(parcel)
        }

        override fun newArray(size: Int): Array<CatImage?> {
            return arrayOfNulls(size)
        }
    }
}