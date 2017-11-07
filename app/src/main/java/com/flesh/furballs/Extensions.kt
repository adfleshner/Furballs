package com.flesh.furballs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * Created by aaronfleshner on 11/7/17.
 */


fun Context.toast(msg:String){
    Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
}


fun ViewGroup.inflate(layoutId:Int, attachToRoot: Boolean = false): View?{
    return LayoutInflater.from(context)?.inflate(layoutId,this,attachToRoot)
}