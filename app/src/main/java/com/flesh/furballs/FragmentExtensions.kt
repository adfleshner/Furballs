package com.flesh.furballs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by aaronfleshner on 7/31/17.
 */


fun ViewGroup.inflate(layoutId:Int,attachToRoot: Boolean = false):View?{
    return LayoutInflater.from(context)?.inflate(layoutId,this,attachToRoot)
}