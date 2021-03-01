package com.example.mitmit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar


class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tb : Toolbar = findViewById(R.id.tb)
        setSupportActionBar(tb)

        val actionBar = supportActionBar
        actionBar!!.title = "APPLICATION"




    }
}