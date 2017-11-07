package com.flesh.furballs.models.shared

import android.os.Parcelable

/**
 * Created by aaronfleshner on 11/5/17.
 */
interface IWebImageResponse :Parcelable {
    var status : String
    var response: List<FurballImage>
}