package com.flesh.furballs.adapters

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.flesh.furballs.*
import com.flesh.furballs.glide.GlideApp
import com.flesh.furballs.glide.GlideRequest
import com.flesh.furballs.models.shared.FurballImage
import kotlinx.android.synthetic.main.list_cell_square_image.view.*
import javax.inject.Inject

/**
 * Created by aaronfleshner on 7/31/17.
 * Images Adapter for RecyclerView to display
 */
class ImagesAdapter(var items: List<FurballImage>, var glide: GlideRequest<Drawable>? = null, var gifGlide: GlideRequest<GifDrawable>? = null, var mClickController: OnCellClickListener? = null) : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {


    interface OnCellClickListener {
        fun onCellClicked(image: FurballImage)
    }

    fun setOnClickListener(listener: OnCellClickListener) {
        mClickController = listener
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder?, position: Int) {
        holder?.bindView(imageUrl = items[position].url)
        holder?.itemView?.setOnClickListener {
            mClickController?.onCellClicked(items[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ImageViewHolder {
        return ImageViewHolder(parent?.inflate(R.layout.list_cell_square_image)!!, glide, gifGlide)
    }


    class ImageViewHolder(itemView: View,
                          private var glide: GlideRequest<Drawable>?,
                          private var gifGlide: GlideRequest<GifDrawable>?) : RecyclerView.ViewHolder(itemView) {
        fun bindView(imageUrl: String) {
            imageUrl.loadInto(itemView.square_image)
        }

        private fun String.loadInto(square_image: ImageView?) {
            val gif_ext = ".gif"
            if (endsWith(gif_ext)) {
                gifGlide?.load(this)?.into(square_image)
            } else {
                glide?.load(this)?.into(square_image)
            }
        }

    }

}



