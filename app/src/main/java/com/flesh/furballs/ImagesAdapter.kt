package com.flesh.furballs

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_cell_square_image.view.*

/**
 * Created by aaronfleshner on 7/31/17.
 */
class ImagesAdapter(var items:List<String>,var mClickController: OnCellClickListener? = null) : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    interface OnCellClickListener{
        fun onCellClicked(url:String)
    }

    fun setOnClickListener(listener: OnCellClickListener){
        mClickController = listener
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder?, position: Int) {
        holder?.bindView(imageUrl = items[position])
        holder?.itemView?.setOnClickListener {
            mClickController?.onCellClicked(items[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ImageViewHolder {
        return ImageViewHolder(parent?.inflate(R.layout.list_cell_square_image)!!)
    }


    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(imageUrl : String) {
            Picasso.with(itemView.context)
                    .load(imageUrl)
                    .placeholder(R.mipmap.ic_launcher_foreground)
                    .error(android.R.drawable.ic_menu_close_clear_cancel)
                    .into(itemView.square_image)
        }
    }
}

