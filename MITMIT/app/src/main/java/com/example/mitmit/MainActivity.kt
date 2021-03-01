package com.example.mitmit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btlogin : Button = findViewById(R.id.btnLogin)
        val edu : EditText = findViewById(R.id.editusername)
        val edp : EditText = findViewById(R.id.editpassword)


        btlogin.setOnClickListener {
            if(edu.text.trim().isNotEmpty() && edp.text.trim().isNotEmpty() )
            {
                Toast.makeText(this,"Input Provided",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,Home::class.java)
                startActivity (intent)
            }
            else
            {
                Toast.makeText(this,"Input Required",Toast.LENGTH_SHORT).show()
            }
        }

        val regis : TextView = findViewById(R.id.toRegis)

        regis.setOnClickListener{
            val intent = Intent(this,Register::class.java)
            startActivity (intent)
        }

    }
}





