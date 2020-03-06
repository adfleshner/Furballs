package com.flesh.furballs.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.flesh.furballs.fragments.ImagesFragment
import com.flesh.furballs.R
import com.flesh.furballs.models.AnimalType
import kotlinx.android.synthetic.main.activity_images.*

class ImagesActivity : AppCompatActivity(){

    private val CURRENT_FRAG: String = "${ImagesActivity::class.java.simpleName} CurrentFragment"

    private lateinit var currentFragment: ImagesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)
        setSupportActionBar(toolbar)
        currentFragment = if (savedInstanceState != null) {
            supportFragmentManager.getFragment(savedInstanceState, CURRENT_FRAG) as ImagesFragment
        } else {
            val type = intent.getSerializableExtra(MainActivity.TYPE) as AnimalType
            ImagesFragment.newInstance(type)
        }
        loadFragments()
    }

    private fun loadFragments() {
        supportFragmentManager.beginTransaction().replace(R.id.content, currentFragment).commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        supportFragmentManager.putFragment(outState, CURRENT_FRAG, currentFragment)
        super.onSaveInstanceState(outState)
    }

}
