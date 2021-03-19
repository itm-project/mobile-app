package com.example.drawer

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator
import me.relex.circleindicator.CircleIndicator3
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class news : AppCompatActivity() {

    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        /*val API = API.retrofitBuild()
        val call = API.getNews()
        call.enqueue(object: Callback<List<newsData>>{
            override fun onResponse(
                call: Call<List<newsData>>,
                response: Response<List<newsData>>
            ) {
                if(response.isSuccessful)
                {
                    val list = response.body()
                    Log.i("API","-------------------------------")
                }
            }

            override fun onFailure(call: Call<List<newsData>>, t: Throwable) {
                Log.e("API",t.message)
            }

        })*/

        /*val imageView : ImageView = findViewById(R.id.imageView)
        imageView.setOnClickListener {
            val  intent = Intent(this,MainActivity::class.java)
            startActivity (intent)
        }*/

        val imageView : ImageView = findViewById(R.id.imageView)
        imageView.setImageResource(R.drawable.warningstamp)
        val imageView2 : ImageView = findViewById(R.id.imageView2)
        imageView2.setImageResource(R.drawable.warningstamp)
        imageView.setOnClickListener {
            imageView.setBackgroundColor(Color.YELLOW)
            imageView2.setBackgroundColor(Color.TRANSPARENT)
        }
        imageView2.setOnClickListener {
            imageView.setBackgroundColor(Color.TRANSPARENT)
            imageView2.setBackgroundColor(Color.YELLOW)
        }

        val btnBH : Button = findViewById(R.id.btnBackHome)
        btnBH.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        postToList()

        val view_pager2 : ViewPager2 = findViewById(R.id.view_pager2)
        view_pager2.adapter =  VIewPagerAdapter(titleList,descList,imagesList)
        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(view_pager2)

    }
    private fun addToList(title : String, description : String , image : Int) {
        titleList.add(title)
        descList.add(description)
        imagesList.add(image)
    }

    private fun postToList() {
        for(i in 1..5)
        {
            addToList(title = "Title $i",description = "Description $i", R.mipmap.ic_launcher_round)
        }
    }

     fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.noti_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.noti -> {
                //Toast.makeText(this,"NOTI",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,notifications::class.java)
                startActivity (intent)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }


    }


}

