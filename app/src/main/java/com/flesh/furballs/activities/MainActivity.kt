package com.flesh.furballs.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.flesh.furballs.fragments.ImagesFragment
import com.flesh.furballs.R

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

}
