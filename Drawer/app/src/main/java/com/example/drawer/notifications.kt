package com.example.drawer

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class notifications : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.title = "Notification"

        actionBar.setDisplayHomeAsUpEnabled(true)

        val picnoti : ImageView = findViewById(R.id.picnoti)
        picnoti.setImageResource(R.drawable.covidnoti)
    }
}