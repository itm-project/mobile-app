package com.example.mitmit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

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
        val mail : EditText = findViewById(R.id.enteremail)
        val user : EditText = findViewById(R.id.enterusername)
        val pw : EditText = findViewById(R.id.enterpassword)
        val cfpw : EditText = findViewById(R.id.confpassword)
        val ad : EditText = findViewById(R.id.enteraddress)

        cbRegis.setOnCheckedChangeListener { buttonView, isChecked ->
            
            if(mail.text.trim().isEmpty() || user.text.trim().isEmpty() || pw.text.trim().isEmpty() || cfpw.text.trim().isEmpty() || ad.text.trim().isEmpty())
            {
                btnRegis.setOnClickListener{
                    Toast.makeText(this,"Input Required",Toast.LENGTH_SHORT).show()
                }
            }
            else if(!isChecked && mail.text.trim().isNotEmpty() && user.text.trim().isNotEmpty() && pw.text.trim().isNotEmpty() && cfpw.text.trim().isNotEmpty() && ad.text.trim().isNotEmpty())
            {
                btnRegis.setOnClickListener{
                    Toast.makeText(this,"Need to Accept Terms",Toast.LENGTH_SHORT).show()
                }
            }
            else if(isChecked && mail.text.trim().isNotEmpty() && user.text.trim().isNotEmpty() && pw.text.trim().isNotEmpty() && cfpw.text.trim().isNotEmpty() && ad.text.trim().isNotEmpty() )
            {
                btnRegis.setOnClickListener{
                    Toast.makeText(this,"Regis!",Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
}