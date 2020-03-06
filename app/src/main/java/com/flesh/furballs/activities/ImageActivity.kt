package com.flesh.furballs.activities

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.flesh.furballs.FurBallsApp
import com.flesh.furballs.R
import com.flesh.furballs.fragments.ImagesFragment
import com.flesh.furballs.glide.GlideRequest
import com.flesh.furballs.models.shared.FurballImage
import com.flesh.furballs.toast
import kotlinx.android.synthetic.main.activity_image.*
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import javax.inject.Inject
import javax.inject.Named


class ImageActivity : AppCompatActivity() {

    @field:[Inject Named("pic")]
    lateinit var glide: GlideRequest<Drawable>

    @field:[Inject Named("gif")]
    lateinit var gifGlide: GlideRequest<GifDrawable>
    lateinit var furball: FurballImage
    private val TAG = "[${ImageActivity::class.java.simpleName}]"


    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        FurBallsApp.appComponent.inject(this)
        setContentView(R.layout.activity_image)
        setSupportActionBar(imagetoolbar)
        if (intent.hasExtra(ImagesFragment.IMAGE_URL)) {
            furball = intent.getParcelableExtra(ImagesFragment.IMAGE_URL)!!
            furball.loadInto(imageView)
        }
    }

    fun FurballImage.loadInto(image: ImageView) {
        val gif_ext = ".gif"
        if (url.endsWith(gif_ext)) {
            gifGlide.load(url).into(image)
        } else {
            glide.load(url).into(image)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.image_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> shareImage()
            R.id.action_save -> {
                val task = @SuppressLint("StaticFieldLeak")
                object : SaveImageTask(this) {
                    override fun onPostExecute(result: Boolean?) {
                        super.onPostExecute(result)
                        toast(if (result == true) {
                            "Saved"
                        } else {
                            "Failed to Save"
                        })
                    }
                }
                task.execute(furball.url)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    @Suppress("DEPRECATION")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun shareImage(): Boolean {
        val share = Intent(android.content.Intent.ACTION_SEND)
        share.type = "text/plain"
        if (Build.VERSION_CODES.LOLLIPOP.targetedApi) {
            share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
        } else {
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
        }
        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Furball!")
        share.putExtra(Intent.EXTRA_TEXT, furball.url)
        startActivity(Intent.createChooser(share, "Share this ball of fur."))
        return true
    }

    companion object {
        open class SaveImageTask(private val context: Context) : AsyncTask<String, Void, Boolean>() {

            private val TAG = "[${SaveImageTask::class.java.simpleName}]"


            override fun doInBackground(vararg p0: String?): Boolean {
                return saveImage(p0[0] ?: "")
            }

            private fun saveImage(url: String): Boolean {
                val DownloadUrl = url
                val fileName = url.split("/")[DownloadUrl.split("/").size - 1]
//                val extension = fileName.split(".")[fileName.split(".").size - 1]

                val folder = File("furballs")
                if (!folder.exists()) folder.mkdir()


                try {
                    val root = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    root?.apply {
                        val dir = File(root.absolutePath + "/furballs")
                        if (!dir.exists()) {
                            dir.mkdirs()
                        }
                        val url = URL(DownloadUrl) //you can write here any link
                        val file = File(dir, fileName)
                        val startTime = System.currentTimeMillis()
                        Log.d(TAG, "saveImage: download begining")
                        Log.d(TAG, "saveImage: download url:" + url)
                        Log.d(TAG, "saveImage: downloaded file name:" + fileName)
                        /* Open a connection to that URL. */
                        val ucon = url.openConnection()
                        /*
                        * Define InputStreams to read from the URLConnection.
                        */
                        val `is` = ucon.getInputStream()
                        val bis = BufferedInputStream(`is`)
                        val fos = FileOutputStream(file)

                        var current: Int
                        do {
                            current = bis.read()
                            fos.write(current)
                        } while (current != -1)
                        fos.flush()
                        fos.close()
                        Log.d("DownloadManager", "download ready in" + (System.currentTimeMillis() - startTime) / 1000 + " sec")
                    } ?: kotlin.run {
                        return false
                    }
                } catch (e: IOException) {
                    Log.d("DownloadManager", "Error: " + e)
                    return false
                }
                return true
            }

        }
    }


}

private val Int.targetedApi: Boolean get() = (this >= Build.VERSION.SDK_INT)





