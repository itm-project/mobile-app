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
        getAddress()

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
                                val msg = "\n username: ${list[i].username} \n name: ${list[i].name} \n lastname: ${list[i].lastname} \n phone: ${list[i].phone} "
                                textviewPro.append(msg)
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

    fun getAddress(){
        val addressId:Int = 9
        api = API.retrofitBuild()
        val call = api.getAddress(addressRequestData(addressId))
        call.enqueue(object : Callback<List<addressData>>{
            override fun onResponse(
                call: Call<List<addressData>>,
                response: Response<List<addressData>>
            ) {
                if(response.isSuccessful)
                {
                    val list = response.body()
                    for(i in 0 until list!!.size)
                    {
                        val msg = "\n Number: ${list[i].number} \n Moo: ${list[i].moo} \n Road: ${list[i].road}  " +
                                  "\n Tambon: ${list[i].TambonThai} \n District: ${list[i].DistrictThai} \n Province: ${list[i].ProvinceThai} " +
                                  "\n Postcode: ${list[i].postcode}"
                        textviewPro.append(msg)
                    }
                    Log.e("ADDRESS ADD!","ADDDDDDDDRESSSSSSSSSSSSSS")
                }
            }
            override fun onFailure(call: Call<List<addressData>>, t: Throwable) {
                Log.e("ADDRESS FAIL!",t.message+" ----------- ADDRESS FAIL")
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater!!.inflate(R.layout.fragment_profile, container, false)

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