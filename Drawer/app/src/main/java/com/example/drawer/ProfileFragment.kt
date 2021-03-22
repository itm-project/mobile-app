package com.example.drawer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
//import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_notifications.*
import kotlinx.android.synthetic.main.activity_notifications.textViewNoti
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit private var api: API
    lateinit var session:sessionUser


    //private lateinit var communicator: Communicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
        //session = sessionUser(requireActivity())
        getProfile()

    }

    fun getProfile() {
        session = sessionUser(requireActivity())
        var user:HashMap<String,String> = session.getUserDetail()
        Log.e("TESTSESSIONINPROFRAG","PROFRAG: "+user)
        var idid :Int
        for ((k, v) in user) {
            if(k == "user_id")
            {
                idid = v.toInt()
                Log.e("NAME IN-ININININ!!!!!!","$k = $v")
                Log.e("ID-ID-ID-ID-ID-ID-ID","ID:"+idid)
                api = API.retrofitBuild()
                val call = api.getProfile(profileRequestData(idid))
                call.enqueue(object : Callback<List<userData>>{
                    override fun onResponse(call: Call<List<userData>>, response: Response<List<userData>>) {
                        if(response.isSuccessful)
                        {
                            val list = response.body()
                            for(i in 0 until list!!.size)
                            {
                                val msg = "\n username: ${list[i].username} \n name: ${list[i].name} \n lastname: ${list[i].lastname} \n phone: ${list[i].phone} \n address: ${list[i].address}"
                                textviewPro.append(msg)
                            }
                            //val msg = "A: "+ (list?.get(0)?.username ?: String)

                            Log.e("GGGGGGGGGGG","GOD PLEASE")
                        }
                        //Log.e("NOTSUCCESSFUL","GOD PLEASE -*-")
                    }
                    override fun onFailure(call: Call<List<userData>>, t: Throwable) {
                        Log.e("FFFFFFFFFF",t.message+"  -DAMN")
                    }

                })
                /*api = API.retrofitBuild()
                val call = api.getNews()
                call.enqueue(object : Callback<List<newsData>> {
                    override fun onResponse(
                        call: Call<List<newsData>>,
                        response: Response<List<newsData>>
                    ) {
                        if (response.isSuccessful) {

                            Log.e("GGGGGGGGGGG","GOD PLEASE")
                            Log.i("API", "--------------- isSuccessful x News at profileFrag ----------------")
                            Log.e("USERNAMEUSERNAME","username:"+username)

                        }
                    }
                    override fun onFailure(call: Call<List<newsData>>, t: Throwable) {
                        Log.e("API", t.message + " -*-*-*-*-*-*-*-*-*-*-*-* x News at profileFrag ")
                    }

                })*/

                Log.e("END-END-END-END-END","ID:"+idid)
            }
        }

    }
    /*api = API.retrofitBuild()
    val call = api.getProfile(profileRequestData(username))
    call.enqueue(object : Callback<userData>{
        override fun onResponse(call: Call<userData>, response: Response<userData>) {
            if(response.isSuccessful)
            {
                val list = response.body()
                val msg = "\n username: ${list!!.username} \n name: ${list!!.name} \n lastname: ${list!!.lastname} \n phone: ${list!!.phone} \n address: ${list!!.address}"
                textviewPro.append(msg)
                Log.e("API", "--------------- isSuccessful x Profile ----------------")
            }
        }
        override fun onFailure(call: Call<userData>, t: Throwable) {
            Log.e("API", t.message + " -*-*-*-*-*-*-*-*-*-*-*-* x Profile ")
        }

    })*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater!!.inflate(R.layout.fragment_profile, container, false)
        //val v: View = inflater!!.inflate(R.layout.activity_login, container, false)
        /*val edu : EditText = v.findViewById(R.id.editusername)
        val edp : EditText = v.findViewById(R.id.editpassword)
        val username = edu.text.toString()
        val password = edp.text.toString()
        Log.e("TESTPROFRAG", "aa: "+username+" bb: "+password)*/
        /*communicator = activity as Communicator
        communicator.passDataCom(v.t)*/
        /*session = sessionUser(requireActivity())
        var user:HashMap<String,String> = session.getUserDetail()
        Log.e("TESTSESSIONINPROFRAG","PROFRAG: "+user)
        for ((k, v) in user) {
            println("$k = $v")
            Log.e("TESTCALLSESSION","$k = $v")
            if(k == "name")
            {
                Log.e("NAME","$k = $v")
            }
            if(k == "lastname")
            {
                Log.e("LASTNAME","$k = $v")
            }
        }*/
        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}