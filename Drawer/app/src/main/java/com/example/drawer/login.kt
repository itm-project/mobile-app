package com.example.drawer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.header.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class login : AppCompatActivity() {

    lateinit var session:sessionUser
    lateinit private var api: API
    var user = ArrayList<userData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        session = sessionUser(applicationContext)

        /*if(session.isLoggedIn())
        {
            var i : Intent = Intent(applicationContext, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            finish()
        }*/

        val btlogin : Button = findViewById(R.id.btnLogin)
        val edu : EditText = findViewById(R.id.editusername)
        val edp : EditText = findViewById(R.id.editpassword)

        //usernameHead.append(edu.text.toString())

        btlogin.setOnClickListener {
            /*val intent = Intent(this,MainActivity::class.java) //----------------------------------------------------------
            startActivity (intent)*/
            if(edu.text.trim().isNotEmpty() && edp.text.trim().isNotEmpty() )
            {
                val username = edu.text.toString()
                val password = edp.text.toString()
                api = API.retrofitBuild()
                val call = api.doLogin(LoginPostData(username,password))
                call.enqueue(object : Callback<List<userData>>{
                    override fun onResponse(
                        call: Call<List<userData>>,
                        response: Response<List<userData>>
                    ) {

                        if(response.isSuccessful)
                        {
                            val list = response.body()
                            for (i in 0 until list!!.size) {
                                user.add(list[i])
                                session.createLoginSession(list[i].user_id.toString(),list[i].name,list[i].lastname)
                            }

                            moveToMain(user)
                            Log.e("API", "--------------- isSuccessful ----------------")
                        }
                        //Log.e("API", "--------------- is -- NOT-NOT-NOT -- Successful ----------------")
                    }

                    override fun onFailure(call: Call<List<userData>>, t: Throwable) {
                        Log.e("API", t.message + " -*-*-*-*-*-*-*-*-*-*-*-* ")
                        moveToMain(user)
                    }

                } )
            }
            else
            {
                Toast.makeText(this,"Input Required", Toast.LENGTH_SHORT).show()
                Log.e("API", "--------------- NEED INPUT ----------------")
            }
        }

        val regis : TextView = findViewById(R.id.toRegis)

        regis.setOnClickListener{
            val intent = Intent(this,register::class.java)
            startActivity (intent)
        }

    }

    private fun moveToMain(user:ArrayList<userData>) {
        if(user.size > 0)
        {
                    Toast.makeText(this,"Input Provided", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity (intent)
            //Toast.makeText(this,"YAY", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(this,"Wrong Input", Toast.LENGTH_SHORT).show()
        }

    }
}