package com.uniworks.grannyapp2025

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

var signedIN : Int =-1
var arrUser = ArrayList<User>()

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate Called")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var Username : EditText = findViewById(R.id.editTextText)
        var Password : EditText = findViewById(R.id.eTPassword)


        arrUser.add(User("Granny", "1234","https://picsum.photos/200/300"))
        arrUser.add(User("Grampa", "4321","https://picsum.photos/200/300"))
        arrUser.add(User("Timmy", "6969","https://picsum.photos/200/300"))
        arrUser.add(User("John", "6565","https://picsum.photos/200/300"));

        var btnLogin : Button = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener(){
            var found = false
            signedIN = -1
            for (i in 0 .. arrUser.size-1){
                if(Username.text.toString().equals(arrUser[i].Name) and
                Password.text.toString().equals(arrUser[i].Password)) {
                    Toast.makeText(this,"name :" +Username.text+"\nPassword :"+Password.text, Toast.LENGTH_SHORT).show()
                    found=true
                    signedIN=i
                    val intent = Intent(this,Welcome::class.java)
                    startActivity(intent)
                    break;
                }
            }
            if (found == false) {
                Toast.makeText(this,"RUNNNNNNNNNNNNNNNN...",Toast.LENGTH_SHORT).show()
            }

        }


    }
}