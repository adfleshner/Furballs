package com.flesh.furballs.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import com.flesh.furballs.*
import com.flesh.furballs.activities.ImageActivity
import com.flesh.furballs.adapters.ImagesAdapter
import com.flesh.furballs.models.WebResponse
import com.flesh.furballs.mvp.presenters.ImagesPresenter
import com.flesh.furballs.mvp.views.ImagesView
import com.flesh.furballs.web.WebRestAPI
import kotlinx.android.synthetic.main.fragment_images.*
import javax.inject.Inject
import android.app.SearchManager
import android.provider.BaseColumns
import android.database.MatrixCursor
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.view.*
import android.text.TextUtils
import com.flesh.furballs.adapters.SearchViewCursorAdapter


/**
 * Images fragment for the Furballs Application.
 * Currently makes a call to the api and displays a grid of the selected Breed
 * Created by aaronfleshner on 7/31/17.
 */
class ImagesFragment : Fragment() , ImagesAdapter.OnCellClickListener , ImagesView,SwipeRefreshLayout.OnRefreshListener {
    val TAG: String = "[${ImagesFragment::class.java.simpleName}]"

    override fun loadBreeds(result: WebResponse) {
        allBreedsResponse = result
        loadBreedsAutoComplete()
    }

    private val breed = "greyhound"
    private val subbreed = "italian"

    private val DATA_KEY: String = "${ImagesFragment::class.java.canonicalName} Data Key"

    var data: WebResponse? = null
    var allBreedsResponse : WebResponse? = null
    var searchItem : MenuItem? = null
    var searchView : SearchView? = null


    @Inject lateinit var restApi : WebRestAPI
    lateinit var imagesPresenter : ImagesPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        getAllBreeds()
    }



    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(DATA_KEY, data)
    }


    fun getImages(){
        if(data ==null){
            loadNewBreed(breed)
        }else{
            updateList()
        }
    }

    fun getAllBreeds(){
        if(allBreedsResponse == null){
            imagesPresenter.loadBreeds()
        }else{
            loadBreedsAutoComplete()
        }
    }


    private fun loadBreedsAutoComplete(query:String="") {
        Thread(Runnable {
            val sAutocompleteColNames = arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1)
            val cursor = MatrixCursor(sAutocompleteColNames)
            // parse your search terms into the MatrixCursor
            for (i in 0..allBreedsResponse!!.message.size-1) {
                val breed = allBreedsResponse!!.message[i]
                if(TextUtils.isEmpty(query)||breed.contains(query)) {
                    val row = arrayOf(i, breed)
                    cursor.addRow(row)
                }
            }
            val cursorAdapter = SearchViewCursorAdapter(context,cursor)
            cursorAdapter.setItemClickListener(object:SearchViewCursorAdapter.OnItemClickListener{
                override fun onItemClick(title: String) {
                    loadNewBreed(title)
                }
            })
            searchView?.suggestionsAdapter =cursorAdapter
            searchView?.suggestionsAdapter!!.changeCursor(cursor)

        }).run()
    }

    private fun loadNewBreed(breed: String) {
        //Load neew breed
        imagesPresenter.loadImages(breed)
        try {
            //Clear query
            searchView?.setQuery("", false)
            //Collapse the action view
            searchView?.onActionViewCollapsed()
            //Collapse the search widget
            searchItem?.collapseActionView()
            //Set Action bar title
            activity.title = breed
        }catch (e:NullPointerException){

        }catch (e2:RuntimeException){

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
        searchItem = menu!!.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                loadBreedsAutoComplete(query = newText?:"")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                loadBreedsAutoComplete(query = query?:"")
                return true
            }
        })
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

