package com.flesh.furballs.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flesh.furballs.*
import com.flesh.furballs.activities.ImageActivity
import com.flesh.furballs.adapters.ImagesAdapter
import com.flesh.furballs.models.WebResponse
import com.flesh.furballs.web.WebRestAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_images.*
import javax.inject.Inject

/**
 * Images fragment for the Furballs Application.
 * Currently makes a call to the api and displays a grid of the selected Breed
 * Created by aaronfleshner on 7/31/17.
 */
class ImagesFragment : Fragment() , ImagesAdapter.OnCellClickListener {

    private val DATA_KEY: String = "${ImagesFragment::class.java.canonicalName} Data Key"

    var data: WebResponse? = null

    @Inject lateinit var restApi : WebRestAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FurBallsApp.appComponent.inject(this)
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
        getImages()
    }



    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(DATA_KEY, data)
    }


    fun getImages(){
        if(data ==null){
            restApi.getDogImages("greyhound","italian").observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        result ->
                        data = result
                        updateList(data)
                    },{error ->
                        error.printStackTrace()
                    })
        }else{
            updateList(data!!)
        }
    }


    override fun onCellClicked(url: String) {
        val intent = Intent(context, ImageActivity::class.java)
        intent.putExtra(IMAGE_URL,url)
        startActivity(intent)
    }

    fun updateList(response: WebResponse?){
        image_list.adapter = ImagesAdapter(response?.message ?: listOf(), this)
        image_list.layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.col_span))
        image_list.setHasFixedSize(true)
    }
}


