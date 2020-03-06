package com.flesh.furballs.models.dog

import android.os.Parcel
import android.os.Parcelable
import com.flesh.furballs.models.shared.IWebQueryResponse
import com.google.gson.annotations.SerializedName

/**
 * Created by aaronfleshner on 11/7/17.
 */
data class DogQueryResponse(@SerializedName("status")
                            override var status : String,
                            @SerializedName("message")
                            override var queries: List<String>) : IWebQueryResponse {
    constructor(parcel: Parcel) : this(
            parcel.readString()?:"",
            parcel.createStringArrayList()?: arrayListOf()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(status)
        parcel.writeStringList(queries)
    }

    override fun describeContents() = 0


    companion object CREATOR : Parcelable.Creator<DogQueryResponse> {
        override fun createFromParcel(parcel: Parcel): DogQueryResponse {
            return DogQueryResponse(parcel)
        }

        override fun newArray(size: Int): Array<DogQueryResponse?> {
            return arrayOfNulls(size)
        }
    }
}