package com.flesh.furballs.activities

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.flesh.furballs.FurBallsApp
import com.flesh.furballs.fragments.ImagesFragment
import com.flesh.furballs.R
import com.flesh.furballs.glide.GlideRequest
import com.flesh.furballs.models.shared.FurballImage
import kotlinx.android.synthetic.main.activity_image.*
import javax.inject.Inject
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import java.io.FileOutputStream
import java.io.File
import android.util.Log
import com.flesh.furballs.toast
import java.io.BufferedInputStream
import java.io.IOException
import java.net.URL


class ImageActivity : AppCompatActivity() {

    @Inject lateinit var glide: GlideRequest<Drawable>
    @Inject lateinit var gifGlide: GlideRequest<GifDrawable>
    lateinit var furball: FurballImage
    private val TAG = "[${ImageActivity::class.java.simpleName}]"


    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        setSupportActionBar(imagetoolbar)
        FurBallsApp.appComponent.inject(this)
        furball = intent.getParcelableExtra(ImagesFragment.IMAGE_URL)
        furball.loadInto(imageView)
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_share -> shareImage()
            R.id.action_save -> {
                val task = @SuppressLint("StaticFieldLeak")
                object : SaveImageTask() {
                    override fun onPostExecute(result: Boolean?) {
                        super.onPostExecute(result)
                        toast(if(result == true){"Saved"} else {"Failed to Save"})
                    }
                }
                task.execute(furball.url)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    @TargetApi(21)
    private fun shareImage(): Boolean {
        val share = Intent(android.content.Intent.ACTION_SEND)
        share.type = "text/plain"
        if (21.targetedApi) {
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
        open class SaveImageTask : AsyncTask<String, Void, Boolean>() {

            private val TAG = "[${SaveImageTask::class.java.simpleName}]"


            override fun doInBackground(vararg p0: String?): Boolean {
                return saveImage(p0[0]?:"")
            }

            private fun saveImage(url:String): Boolean {
                val DownloadUrl = url
                val fileName = url.split("/")[DownloadUrl.split("/").size-1]
                val extension = fileName.split(".")[fileName.split(".").size - 1]

                val folder = File("furballs")
                if(!folder.exists()) folder.mkdir()


                try {
                    val root = android.os.Environment.getExternalStorageDirectory()
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





