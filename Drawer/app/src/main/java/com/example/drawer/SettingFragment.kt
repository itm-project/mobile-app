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
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_setting.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {
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
                call.enqueue(object : Callback<List<userData>> {
                    override fun onResponse(call: Call<List<userData>>, response: Response<List<userData>>) {
                        if(response.isSuccessful)
                        {
                            val list = response.body()
                            for(i in 0 until list!!.size)
                            {
                                //val msg = "\n username: ${list[i].username} \n name: ${list[i].name} \n lastname: ${list[i].lastname} \n phone: ${list[i].phone} "
                                val usern = " Username: ${list[i].username} "
                                tv0.append(usern)
                                val name = " Name: ${list[i].name}  ${list[i].lastname}"
                                tv1.append(name)
                                val pho = "Phone: ${list[i].phone}"
                                tv2.append(pho)
                                val addre = "Address: ${list[i].address}"
                                tv4.append(addre)
                                //getAddress(list[i].address)
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

                Log.e("END-END-END-END-END","ID:"+idid)
            }
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View = inflater!!.inflate(R.layout.fragment_setting, container, false)
        val btusername:Button = v.findViewById(R.id.btnStUsername)
        val tvusername : TextView = v.findViewById(R.id.tv0)
        val et0 : EditText = v.findViewById(R.id.et0)

        btusername.setOnClickListener {
            val et0st :String = et0.text.toString()
            Log.e("EDITTEXT0","t->"+et0st)
            if(et0st.length != 0 )
            {
                tvusername.setText("Username: "+et0st)
            }

            //startActivity(Intent(activity,EditAllSetting::class.java))
        }
        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}