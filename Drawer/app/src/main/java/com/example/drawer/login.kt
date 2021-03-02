package com.example.drawer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val btlogin : Button = findViewById(R.id.btnLogin)
        val edu : EditText = findViewById(R.id.editusername)
        val edp : EditText = findViewById(R.id.editpassword)


        btlogin.setOnClickListener {
            if(edu.text.trim().isNotEmpty() && edp.text.trim().isNotEmpty() )
            {
                Toast.makeText(this,"Input Provided", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,MainActivity::class.java)
                startActivity (intent)
            }
            else
            {
                Toast.makeText(this,"Input Required", Toast.LENGTH_SHORT).show()
            }
        }

        val regis : TextView = findViewById(R.id.toRegis)

        regis.setOnClickListener{
            val intent = Intent(this,register::class.java)
            startActivity (intent)
        }

    }
}