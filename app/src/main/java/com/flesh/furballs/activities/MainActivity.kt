package com.flesh.furballs.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.flesh.furballs.fragments.ImagesFragment
import com.flesh.furballs.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    private val CURRENT_FRAG: String? = "${MainActivity::class.java.simpleName} CurrentFragment"

    lateinit var currentFragment: ImagesFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        if (savedInstanceState != null) {
            currentFragment = supportFragmentManager.getFragment(savedInstanceState, CURRENT_FRAG) as ImagesFragment
        } else {
            currentFragment = ImagesFragment.newInstance()
        }
        loadFragments()
    }

    private fun loadFragments() {
        supportFragmentManager.beginTransaction().replace(R.id.content, currentFragment).commit()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        supportFragmentManager.putFragment(outState, CURRENT_FRAG, currentFragment)
        super.onSaveInstanceState(outState)
    }

}
