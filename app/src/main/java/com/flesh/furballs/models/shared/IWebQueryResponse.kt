package com.flesh.furballs.models.shared

import android.os.Parcelable

/**
 * Created by aaronfleshner on 11/7/17.
 */
interface IWebQueryResponse : Parcelable{
    val status : String
    val queries : List<String>
}