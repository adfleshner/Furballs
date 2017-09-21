package com.flesh.furballs.fragments

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.DialogInterface.*
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flesh.furballs.*
import com.flesh.furballs.activities.ImageActivity
import com.flesh.furballs.adapters.ImagesAdapter
import com.flesh.furballs.models.WebResponse
import com.flesh.furballs.mvp.presenters.ImagesPresenter
import com.flesh.furballs.mvp.views.ImagesView
import com.flesh.furballs.web.WebRestAPI
import kotlinx.android.synthetic.main.fragment_images.*
import javax.inject.Inject

/**
 * Images fragment for the Furballs Application.
 * Currently makes a call to the api and displays a grid of the selected Breed
 * Created by aaronfleshner on 7/31/17.
 */
class ImagesFragment : Fragment() , ImagesAdapter.OnCellClickListener , ImagesView,SwipeRefreshLayout.OnRefreshListener {


    private val breed = "greyhound"
    private val subbreed = "italian"

    private val DATA_KEY: String = "${ImagesFragment::class.java.canonicalName} Data Key"

    var data: WebResponse? = null

    @Inject lateinit var restApi : WebRestAPI
    lateinit var imagesPresenter : ImagesPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FurBallsApp.appComponent.inject(this)
        imagesPresenter = ImagesPresenter(restApi,this)
    }

    companion object {
        val IMAGE_URL: String = "${ImagesFragment::class.java.simpleName} image url key"
        fun newInstance(): ImagesFragment {
            val frag = ImagesFragment()
            val args = Bundle()
            frag.arguments = args
            return frag
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_images)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //check if data is in the saved instance.
        data = savedInstanceState?.getParcelable(DATA_KEY)
        image_srl.setOnRefreshListener(this)
        getImages()
    }



    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(DATA_KEY, data)
    }


    fun getImages(){
        if(data ==null){
           imagesPresenter.loadImages(breed)
        }else{
            updateList()
        }
    }

    override fun onCellClicked(url: String) {
        val intent = Intent(context, ImageActivity::class.java)
        intent.putExtra(IMAGE_URL,url)
        startActivity(intent)
    }

    fun updateList(){
        if(data?.message?.size?:0 != 0 ) {
            image_list.adapter = ImagesAdapter(this.data?.message ?: listOf(), this)
            image_list.layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.col_span))
            image_list.setHasFixedSize(true)
        }else{
            showEmpty()
        }
    }

    override fun onRefresh() {
        imagesPresenter.loadImages(breed)
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
        image_srl.isRefreshing = true
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
        image_srl.isRefreshing = false
    }

    override fun showEmpty() {
        empty.visibility = View.VISIBLE
    }

    override fun hideEmpty() {
        empty.visibility = View.GONE
    }

    override fun ErrorLoaded(error: Throwable) {
        showError(error)
    }

    private fun showError(error: Throwable) {
        error.printStackTrace()
        AlertDialog.Builder(context).setMessage(error.message).setTitle(R.string.error)
                .setPositiveButton(R.string.retry, { _, _ ->
                    imagesPresenter.loadImages(breed)
                })
                .setNegativeButton(R.string.cancel, { _, _ ->
                    showEmpty()
                }).setCancelable(false)
                .show()
    }

    override fun dataLoaded(data: WebResponse) {
        //Update data
        this.data = data
        //Show in list.
        updateList()
    }


}


