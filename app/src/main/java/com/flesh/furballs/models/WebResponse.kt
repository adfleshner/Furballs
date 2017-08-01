package com.flesh.furballs.models

/**
 * Created by aaronfleshner on 7/31/17.
 * Simple data class for a web response from the dog api
 */
data class WebResponse(var status : String, var message: List<String>)