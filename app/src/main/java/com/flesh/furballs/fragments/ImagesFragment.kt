package com.flesh.furballs.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.flesh.furballs.*
import com.flesh.furballs.activities.ImageActivity
import com.flesh.furballs.adapters.ImagesAdapter
import com.flesh.furballs.mvp.presenters.ImagesPresenter
import com.flesh.furballs.mvp.views.ImagesView
import kotlinx.android.synthetic.main.fragment_images.*
import javax.inject.Inject
import android.app.SearchManager
import android.provider.BaseColumns
import android.database.MatrixCursor
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.SearchView
import android.view.*
import android.text.TextUtils
import android.util.Log
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.flesh.furballs.adapters.SearchViewCursorAdapter
import com.flesh.furballs.glide.GlideRequest
import com.flesh.furballs.models.AnimalType
import com.flesh.furballs.models.shared.FurballImage
import com.flesh.furballs.models.shared.IWebImageResponse
import com.flesh.furballs.models.shared.IWebQueryResponse
import com.flesh.furballs.web.BaseWebProvider
import java.util.*
import javax.inject.Named


/**
 * Images fragment for the Furballs Application.
 * Currently makes a call to the api and displays a grid of the selected Breed
 * Created by aaronfleshner on 7/31/17.
 */
class ImagesFragment : Fragment(), ImagesAdapter.OnCellClickListener, ImagesView, SwipeRefreshLayout.OnRefreshListener {

    override fun hideSearch() {
        setHasOptionsMenu(false)
    }


    override fun loadQueries(result: IWebQueryResponse) {
        allBreedsResponse = result
        loadBreedsAutoComplete()
    }

    private val DEFAULT_DOG = "greyhound"
    private val DEFAULT_CAT = "Cat"

    private var breed = DEFAULT_DOG
    private val category: String? = null

    private val DATA_KEY: String = "${ImagesFragment::class.java.canonicalName} Data Key"

    var data: IWebImageResponse? = null
    var allBreedsResponse: IWebQueryResponse? = null
    var searchItem: MenuItem? = null
    var searchView: SearchView? = null
    lateinit var type: AnimalType

    lateinit var cursorAdapter: SearchViewCursorAdapter
    lateinit var cursor: MatrixCursor


    @field:[Inject Named("api")]
    lateinit var restApi: BaseWebProvider
    @field:[Inject Named("pic")]
    lateinit var glide: GlideRequest<Drawable>
    @field:[Inject Named("gif")]
    lateinit var gifGlide: GlideRequest<GifDrawable>

    lateinit var imagesPresenter: ImagesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val animal = arguments?.get(TYPE)
        type = when (animal) {
            AnimalType.DOG -> {
                breed = DEFAULT_DOG
                AnimalType.DOG
            }
            AnimalType.CAT -> {
                breed = DEFAULT_CAT
                AnimalType.CAT
            }
            else -> throw Throwable("$animal not supported")
        }
        FurBallsApp.appComponent.inject(this)
        imagesPresenter = ImagesPresenter(restApi, this)
    }

    companion object {
        @JvmStatic
        val TAG: String = "[${ImagesFragment::class.java.canonicalName}]"
        @JvmStatic
        val IMAGE_URL: String = "${ImagesFragment::class.java.simpleName} image url key"
        @JvmStatic
        val TYPE: String = "$TAG Type"

        /**
         * The Images Fragment Instance is a list of Images in a grid.
         */
        @JvmStatic
        fun newInstance(type: AnimalType): ImagesFragment {
            val frag = ImagesFragment()
            val args = Bundle()
            args.putSerializable(TYPE, type)
            frag.arguments = args
            return frag
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_images)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //check if data is in the saved instance.
        data = savedInstanceState?.getParcelable(DATA_KEY)
        image_srl.setOnRefreshListener(this)
        getImages()
        getAllBreeds()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(DATA_KEY, data)
    }

    override fun loadImages(result: IWebImageResponse) {
        data = result
        updateList()
    }


    private fun getImages() {
        if (data == null) {
            loadNewBreed(breed, category, type)
        } else {
            updateList()
        }
    }

    private fun getAllBreeds() {
        if (allBreedsResponse == null) {
            imagesPresenter.loadFilters(type)
        } else {
            loadBreedsAutoComplete()
        }
    }


    private fun loadBreedsAutoComplete(query: String = "") {
        Thread(Runnable {
            val sAutocompleteColNames = arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1)
            cursor = MatrixCursor(sAutocompleteColNames)
            // parse your search terms into the MatrixCursor
            if (allBreedsResponse != null) {
                for (i in 0 until allBreedsResponse!!.queries.size) {
                    val breed = allBreedsResponse!!.queries[i]
                    if (TextUtils.isEmpty(query) || breed.contains(query)) {
                        val row = arrayOf(i, breed)
                        cursor.addRow(row)
                    }
                }
                cursorAdapter = SearchViewCursorAdapter(context!!, cursor)
                cursorAdapter.setItemClickListener(object : SearchViewCursorAdapter.OnItemClickListener {
                    override fun onItemClick(title: String) {
                        loadNewBreed(title, title, type)
                    }
                })
                updateSearchMenu()
            } else {
                Log.e(TAG, "Categories is null")
            }
        }).run()
    }


    private fun loadNewBreed(breed: String, category: String?, type: AnimalType) {
        //Load new breed
        imagesPresenter.loadImages(breed, category, type)
        try {
            //Clear query
            searchView?.setQuery("", false)
            //Collapse the action view
            searchView?.onActionViewCollapsed()
            //Collapse the search widget
            searchItem?.collapseActionView()
            //Set Action bar title
            activity!!.title = breed
        } catch (e: NullPointerException) {
            Log.e(TAG, "loadNewBreed: ${e.localizedMessage}")
        } catch (e2: RuntimeException) {
            Log.e(TAG, "loadNewBreed: ${e2.localizedMessage}")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                loadBreedsAutoComplete(query = newText ?: "")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                loadBreedsAutoComplete(query = query ?: "")
                return true
            }
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        updateSearchMenu()
    }

    override fun onCellClicked(image: FurballImage) {
        val intent = Intent(context, ImageActivity::class.java)
        intent.putExtra(IMAGE_URL, image)
        startActivity(intent)
    }

    fun updateSearchMenu(){
        try {
            searchView?.suggestionsAdapter = cursorAdapter
            searchView?.suggestionsAdapter!!.changeCursor(cursor)
        }catch (e: Exception){
            Log.d(TAG,"Loading Searchview too early")
        }
    }

    private fun updateList() {
        if (data?.response?.size ?: 0 != 0) {
            image_list.adapter = ImagesAdapter(this.data?.response?.shuffle() as ArrayList<FurballImage>? ?: arrayListOf(), glide, gifGlide, this)
            image_list.layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.col_span))
            image_list.setHasFixedSize(true)
        } else {
            showEmpty()
        }
    }

    override fun onRefresh() {
        imagesPresenter.loadImages(breed, category, type)
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
        Log.e(TAG, "showError: ${error.stackTrace}")
        AlertDialog.Builder(context).setMessage(error.message).setTitle(R.string.error)
                .setPositiveButton(R.string.retry, { _, _ ->
                    imagesPresenter.loadImages(breed, category, type)
                })
                .setNegativeButton(R.string.cancel, { _, _ ->
                    showEmpty()
                }).setCancelable(false)
                .show()
    }

    override fun dataLoaded(data: IWebImageResponse) {
        //Update list
        this.data = data
        //Show in list.
        updateList()
    }

}

/**
 * Returns a randomized list.
 */
fun <T> Iterable<T>.shuffle(seed: Long? = null): List<T> {
    val list = this.toMutableList()
    val random = if (seed != null) Random(seed) else Random()
    Collections.shuffle(list, random)
    return list
}

