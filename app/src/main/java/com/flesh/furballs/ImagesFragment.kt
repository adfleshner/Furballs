package com.flesh.furballs

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_images.*

/**
 * Created by aaronfleshner on 7/31/17.
 */
class ImagesFragment : Fragment() ,ImagesAdapter.OnCellClickListener{



    lateinit var service: WebService
    var response : WebResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        service = WebService(context)
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
        getImages()
    }


    fun getImages(){
        if(response==null){
            service.getImages("greyhound").observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        result ->
                        response = result
                        updateList(response)
                    },{error ->
                        error.printStackTrace()
                    })
        }else{
            updateList(response!!)
        }
    }


    override fun onCellClicked(url: String) {
        var intent = Intent(context,ImageActivity::class.java)
        intent.putExtra(IMAGE_URL,url)
        startActivity(intent)
    }

    fun updateList(response: WebResponse?){
        image_list.adapter = ImagesAdapter(response?.message?: listOf(),this)
        image_list.layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.col_span))
        image_list.setHasFixedSize(true)
    }
}


