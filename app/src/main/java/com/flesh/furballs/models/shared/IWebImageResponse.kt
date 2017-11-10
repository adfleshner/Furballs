package com.flesh.furballs.models.shared

import android.os.Parcelable

/**
 * Created by aaronfleshner on 11/5/17.
 */
interface IWebImageResponse :Parcelable {
    val status : String
    val response: List<FurballImage>
}