package com.flesh.furballs.adapters

import android.app.SearchManager
import android.content.Context
import android.database.Cursor
import android.support.v4.widget.CursorAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.flesh.furballs.R

/**
 * Created by aaronfleshner on 10/18/17.
 * A cursor adapter for the autocomplete search view
 */
class SearchViewCursorAdapter(var context: Context,cursor: Cursor): CursorAdapter(context,cursor,1) {
    var listener : OnItemClickListener? = null
    val layoutInflator : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun newView(context: Context?, c: Cursor?, parent: ViewGroup?): View {
        return layoutInflator.inflate(R.layout.list_cell_item,parent,false)
    }

    override fun bindView(view: View?, context: Context?, c: Cursor?) {
        val title = view?.findViewById<TextView>(R.id.tvSearchItem)
        val text = cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
        title?.text = text
        view?.setOnClickListener { listener?.onItemClick(text) }
    }


    interface OnItemClickListener{
        fun onItemClick(title:String)
    }

    fun setItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}