package com.flesh.furballs.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.flesh.furballs.*
import com.flesh.furballs.glide.GlideApp
import com.flesh.furballs.models.shared.FurballImage
import kotlinx.android.synthetic.main.list_cell_square_image.view.*

/**
 * Created by aaronfleshner on 7/31/17.
 * Images Adapter for RecyclerView to display
 */
class ImagesAdapter(var items: List<FurballImage>, var mClickController: OnCellClickListener? = null) : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

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
        return ImageViewHolder(parent?.inflate(R.layout.list_cell_square_image)!!)
    }


    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(imageUrl: String) {
            val placeholder = R.mipmap.ic_launcher_foreground
            val error = android.R.drawable.ic_menu_close_clear_cancel
            val image = itemView.square_image
            if (imageUrl.endsWith(".gif")) {
                GlideApp
                        .with(itemView.context)
                        .asGif()
                        .load(imageUrl)
                        .centerCrop()
                        .placeholder(placeholder)
                        .error(error)
                        .into(image)
            } else {
                GlideApp
                        .with(itemView.context)
                        .load(imageUrl)
                        .centerCrop()
                        .placeholder(placeholder)
                        .error(error)
                        .into(image)
            }
        }
    }


}

