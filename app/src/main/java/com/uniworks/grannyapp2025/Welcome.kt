package com.uniworks.grannyapp2025

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors

class Welcome : AppCompatActivity() {
    private val TAG2 = "Welcome"
    lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        Log.d(TAG2, "onCreate Called")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var tvOutput : TextView = findViewById(R.id.tvPassword)
        tvOutput.setText("welcome : \n"+arrUser[signedIN].Name)
        var image:Bitmap? = null;
        val imOutput:ImageView = findViewById(R.id.imWelcome);
        val handler = Handler(Looper.getMainLooper())
        val executor = Executors.newSingleThreadExecutor()
        executor.execute{
            val imageURL = arrUser[signedIN].imageURL
            try{
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
                Log.d(TAG2,"Image has been added "+ image.toString())
                handler.post{
                    Log.d(TAG2,"image has been added")
                    imOutput.setImageBitmap(image)
                }
            }
            catch(e:java.lang.Exception){
                Log.d(TAG2, "error happenned ... oh no .. "+e.toString())
                e.printStackTrace()
            }
        }

        val feed :RecyclerView= findViewById(R.id.recyclerView)
        userAdapter = UserAdapter()
        feed.apply {
            layoutManager=LinearLayoutManager(this@Welcome)
            adapter = userAdapter

        }
        val items = mutableListOf<User>()
        for (i in 0 .. 40){
            items.add(
                User(
                 Name="Name Test $i", Password = "Password$i", imageURL = "https://picsum.photos/200/300"
                )
            )
        }
        userAdapter.submitList(items)
    }
}