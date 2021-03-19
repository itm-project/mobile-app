package com.example.drawer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_home.*
import me.relex.circleindicator.CircleIndicator3
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NullPointerException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()

    lateinit private var api: API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //postToList()
        api = API.retrofitBuild()
        postNews()

    }

    private fun addToList(title: String, description: String, image: Int) {
        titleList.add(title)
        descList.add(description)
        imagesList.add(image)
    }

    /*private fun postToList() {
        for (i in 1..5) {
            addToList(
                title = "Title $i",
                description = "Description $i",
                R.mipmap.ic_launcher_round
            )
        }
    }*/

//: Call<List<newsData>>
    private fun postNews() {
        val call= api.getNews()
        call.enqueue(object : Callback<List<newsData>> {
            override fun onResponse(
                call: Call<List<newsData>>,
                response: Response<List<newsData>>
            ) {
                if (response.isSuccessful) {
                    val list = response.body()
                    Log.i("API", "--------------- isSuccessful x News ----------------")

                    for (i in 0 until list!!.size) {
                        addToList(
                            title = "Title : ${list[i].name}",
                            description = "Description : ${list[i].detail}",
                            R.mipmap.ic_launcher_round
                        )
                    }
                }
            }

            override fun onFailure(call: Call<List<newsData>>, t: Throwable) {
                Log.e("API", t.message + " -*-*-*-*-*-*-*-*-*-*-*-* x News")
            }

        })
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater!!.inflate(R.layout.fragment_home, container, false)
        val view_pager2 : ViewPager2 = v.findViewById(R.id.view_pager2)
        view_pager2.adapter = VIewPagerAdapter(titleList, descList, imagesList)
        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL



        val indicator = v.findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(view_pager2)

        val btnWarning : Button = v.findViewById(R.id.btnWarning)
        btnWarning.setOnClickListener {
            startActivity(Intent(activity,news::class.java))
        }
        // Inflate the layout for this fragment
        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}