package com.flesh.furballs.models.dog

import android.os.Parcel
import android.os.Parcelable
import com.flesh.furballs.models.shared.FurballImage
import com.flesh.furballs.models.shared.IWebImageResponse
import com.google.gson.annotations.SerializedName

/**
 * Created by aaronfleshner on 7/31/17.
 * Simple data class for a web data from the dog api
 */
data class DogImageResponse(@SerializedName("status")
                       override val status : String,
                            @SerializedName("message")
                       private var message: List<String>) : IWebImageResponse {
    override val response: List<FurballImage>
        get() = message.map { FurballImage(it, it) }

    constructor(parcel: Parcel) : this(
            parcel.readString()?:"",
            parcel.createStringArrayList()?: arrayListOf()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(status)
        parcel.writeStringList(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DogImageResponse> {
        override fun createFromParcel(parcel: Parcel): DogImageResponse {
            return DogImageResponse(parcel)
        }

        override fun newArray(size: Int): Array<DogImageResponse?> {
            return arrayOfNulls(size)
        }
    }

}