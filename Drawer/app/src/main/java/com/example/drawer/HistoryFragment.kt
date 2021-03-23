package com.example.drawer

import android.content.pm.PackageManager
import android.net.DnsResolver
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.fragment_history.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit private var api: API
    lateinit var session:sessionUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        getLocation()
    }

    private fun getLocation(){
        session = sessionUser(requireActivity())
        var shareloma: HashMap<String, String> = session.getLocationDetail()
        //var user: HashMap<String, String> = session.getUserDetail()
        Log.e("TESTSESSIONINLOCATION","PROFRAG: "+shareloma)
        api = API.retrofitBuild()
        val call = api.getLocation(historyRequestData(shareloma.getValue("latitude"),shareloma.getValue("longitude"),"admin")) //**********
        call.enqueue(object : Callback<List<getHistoryData>>{
            override fun onResponse(
                call: Call<List<getHistoryData>>,
                response: Response<List<getHistoryData>>
            ) {
                if(response.isSuccessful)
                {
                    val list = response.body()
                    for(i in 0 until list!!.size)
                    {
                        val msg = "\n\n Place: ${list[i].name} \n Time: ${list[i].time} \n Latitude: ${list[i].latitude} \n Longitude: ${list[i].longtitude} \n"
                        tvHistory.append(msg)
                    }

                }
            }

            override fun onFailure(call: Call<List<getHistoryData>>, t: Throwable) {
                Log.e("IN HIS",t.message+"  -HISTORY FAIL")
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HistoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}