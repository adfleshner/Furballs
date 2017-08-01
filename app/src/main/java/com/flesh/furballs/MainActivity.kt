package com.flesh.furballs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val CURRENT_FRAG: String? = "${MainActivity::class.java.simpleName} CurrentFragment"
    var currentFragment = ImagesFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment()
    }

    private fun loadFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.content, currentFragment).commit()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        currentFragment = supportFragmentManager.getFragment(savedInstanceState, CURRENT_FRAG) as ImagesFragment
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        supportFragmentManager.putFragment(outState, CURRENT_FRAG, currentFragment)
        super.onSaveInstanceState(outState)
    }

}
