package com.example.drawer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_notifications.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class notifications : AppCompatActivity() {

    lateinit private var api: API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.title = "Notification"

        actionBar.setDisplayHomeAsUpEnabled(true)

        api = API.retrofitBuild()
        getNotiNews()

    }

    private fun getNotiNews(){
        val call = api.getNotiNews()
        call.enqueue(object: Callback<List<notiData>> {
            override fun onResponse(
                call: Call<List<notiData>>,
                response: Response<List<notiData>>
            ) {
                if(response.isSuccessful)
                {
                    val list = response.body()
                    Log.i("API","--------------- isSuccessful x NotiNews ----------------")

                    for(i in 0 until list!!.size)
                    {
                        val msg = " \n name: ${list[i].name} \n detail: ${list[i].detail} \n date: ${list[i].date} \n "
                        textViewNoti.append(msg)
                    }
                }
            }

            override fun onFailure(call: Call<List<notiData>>, t: Throwable) {
                Log.e("API",t.message+" -*-*-*-*-*-*-*-*-*-*-*-* x NotiNews")
            }

        })
    }
}