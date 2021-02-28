package com.example.mitmit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val log : TextView = findViewById(R.id.toLog)

        log.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity (intent)
        }

        val btnRegis : Button = findViewById(R.id.btnRegis)
        val cbRegis : CheckBox = findViewById(R.id.cbTerms)

        btnRegis.setOnClickListener{
            Toast.makeText(this,"Regis!",Toast.LENGTH_SHORT).show()
        }
    }
}