package com.flesh.furballs.models.cat

import android.os.Parcel
import android.os.Parcelable
import com.flesh.furballs.models.shared.FurballImage
import com.flesh.furballs.models.shared.IWebImageResponse
import com.flesh.furballs.models.shared.IWebQueryResponse
import com.google.gson.annotations.SerializedName

/**
 * Created by aaronfleshner on 11/6/17.
 */
data class CatResponse(@SerializedName("response")var res:Response?) : IWebImageResponse, IWebQueryResponse {

    override val status: String
        get() = if (res!=null) {"success"} else {"failed"}
    override val response: List<FurballImage>
        get() = (res?.data?.images?.image?: listOf()).map{ FurballImage(it.url, it.source_url) }
    override val queries: List<String>
        get() = (res?.data?.categories?.category?: listOf()).map{ it.name }

    constructor(parcel: Parcel) : this(parcel.readParcelable<Response>(Response::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(res, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CatResponse> {
        override fun createFromParcel(parcel: Parcel): CatResponse {
            return CatResponse(parcel)
        }

        override fun newArray(size: Int): Array<CatResponse?> {
            return arrayOfNulls(size)
        }
    }


}