package com.flesh.furballs.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.flesh.furballs.R
import com.flesh.furballs.models.AnimalType
import com.flesh.furballs.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener {
    companion object {
       @JvmStatic val TYPE = "${MainActivity::class.java} Animal Type"
    }


    override fun onClick(p0: View?) {
        val intent = Intent(this@MainActivity, ImagesActivity::class.java)
        when (p0?.id) {
            R.id.btn_dog -> {
                intent.putExtra(TYPE,AnimalType.DOG)
                startActivity(intent)
            }
            R.id.btn_cat ->{
                intent.putExtra(TYPE,AnimalType.CAT)
                startActivity(intent)
            }
            else -> toast("Choose Either Cat or Dog")
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_cat.setOnClickListener(this)
        btn_dog.setOnClickListener(this)
    }

}





