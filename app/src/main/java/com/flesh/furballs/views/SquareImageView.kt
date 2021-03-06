package com.flesh.furballs.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by aaronfleshner on 7/31/17.
 * Simple Class to create a square ImageView
 */
class SquareImageView : ImageView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

}