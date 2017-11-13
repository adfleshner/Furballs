package com.flesh.furballs.web.parsing

import android.content.Context
import com.flesh.furballs.models.dog.DogImageResponse
import com.flesh.furballs.models.shared.IWebImageResponse
import com.google.gson.InstanceCreator
import java.lang.reflect.Type

/**
 * Created by aaronfleshner on 11/5/17.
 */
class WebResponseInterfaceInstanceCreator(var context: Context) : InstanceCreator<IWebImageResponse>{
    override fun createInstance(type: Type?): IWebImageResponse {
        var result : IWebImageResponse?
        result = DogImageResponse("", listOf())
        return result
    }
}