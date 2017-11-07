package com.flesh.furballs.models.cat

import android.os.Parcel
import android.os.Parcelable
import com.flesh.furballs.models.shared.FurballImage
import com.flesh.furballs.models.shared.IWebImageResponse
import com.google.gson.annotations.SerializedName

/**
 * Created by aaronfleshner on 11/6/17.
 */
data class CatResponse(@SerializedName("response")var res:Response?) : IWebImageResponse {

    override var status: String
        get() = if (res!=null) {"success"} else {"failed"}
        set(value) {}

    override var response: List<FurballImage>
        get() = (res?.data?.images?.image?: listOf()).map{ FurballImage(it.url, it.source_url) }
        set(value) {}

    constructor(parcel: Parcel) : this(parcel.readParcelable<Response>(Response::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(res, flags)
    }

    override fun describeContents()= 0

    companion object CREATOR : Parcelable.Creator<CatResponse> {
        override fun createFromParcel(parcel: Parcel): CatResponse {
            return CatResponse(parcel)
        }

        override fun newArray(size: Int): Array<CatResponse?> {
            return arrayOfNulls(size)
        }
    }


}