package com.flesh.furballs.adapters

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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
class ImagesAdapter(private var items: ArrayList<FurballImage>, var glide: GlideRequest<Drawable>? = null, var gifGlide: GlideRequest<GifDrawable>? = null, var mClickController: OnCellClickListener? = null) : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {


    interface OnCellClickListener {
        fun onCellClicked(image: FurballImage)
    }

    /**
     * to set the ImageAdapters Click Listener
     */
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
        return ImageViewHolder(items,this,parent?.inflate(R.layout.list_cell_square_image)!!, glide, gifGlide)
    }


    /**
     * The ImageViewHolder that Holds the View and removes it if the image fails to load.
     */
    class ImageViewHolder(var items: ArrayList<FurballImage>,
                          var adapter: ImagesAdapter,itemView: View,
                          private var glide: GlideRequest<Drawable>?,
                          private var gifGlide: GlideRequest<GifDrawable>?) : RecyclerView.ViewHolder(itemView) {
        fun bindView(imageUrl: String) {
            imageUrl.loadInto(itemView.square_image)
        }

        private val TAG= "[${ImageViewHolder::class.java}]"

        private fun String.loadInto(square_image: ImageView?) {
            val gif_ext = ".gif"
            if (endsWith(gif_ext)) {
                gifGlide?.listener(removeGifIfFailed)?.load(this)?.into(square_image)
            } else {
                glide?.listener(removeIfFailed)?.load(this)?.into(square_image)
            }
        }


        private val removeIfFailed = object : RequestListener<Drawable>{
                override fun onResourceReady(resource: Drawable?, model: Any?,
                                             target: Target<Drawable>?, dataSource: DataSource?,
                                             isFirstResource: Boolean): Boolean {
                    //run normally
                    return false
                }

                override fun onLoadFailed(e: GlideException?, model: Any?,
                                          target: com.bumptech.glide.request.target.Target<Drawable>?,
                                          isFirstResource: Boolean): Boolean {
                    //Remove the Item.
                    removeItemAt(items,adapter, adapterPosition)
                    return true
                }
            }


        private val removeGifIfFailed = object : RequestListener<GifDrawable>{
                override fun onResourceReady(resource: GifDrawable?, model: Any?,
                                             target: Target<GifDrawable>?, dataSource: DataSource?,
                                             isFirstResource: Boolean): Boolean {
                    //run normally
                    return false
                }

                override fun onLoadFailed(e: GlideException?, model: Any?,
                                          target: com.bumptech.glide.request.target.Target<GifDrawable>?,
                                          isFirstResource: Boolean): Boolean {
                    //Remove the Item.
                    removeItemAt(items,adapter, adapterPosition)
                    return true
                }
            }


        private fun removeItemAt(items: ArrayList<FurballImage>, adapter: ImagesAdapter, position: Int){
            try {
                items.removeAt(position)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, items.size)
                Log.d(TAG, "Removed item at $position")
            }catch (e:ArrayIndexOutOfBoundsException){
                Log.d(TAG, "ArrayIndexOutOfBoundsException at $position")
            }
        }

    }



}



