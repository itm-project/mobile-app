package com.example.testappmit

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val toolbar:Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar!!.title = "APPLICATION"


        val button : Button = findViewById(R.id.button)

        button.setOnClickListener{
            val intent = Intent(this,Activity2::class.java)
            startActivity (intent)
        }



    }
}