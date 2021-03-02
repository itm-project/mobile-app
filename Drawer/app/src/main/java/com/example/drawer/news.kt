package com.example.drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator
import me.relex.circleindicator.CircleIndicator3

class news : AppCompatActivity() {

    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

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


}

