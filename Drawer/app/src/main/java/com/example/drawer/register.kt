package com.example.drawer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegis: Button = findViewById(R.id.btnRegis)
        val cbRegis: CheckBox = findViewById(R.id.cbTerms)
        val name: EditText = findViewById(R.id.name)
        val lastName: EditText = findViewById(R.id.lastName)
        val phone: EditText = findViewById(R.id.phone)
        val mail: EditText = findViewById(R.id.enteremail)
        val username: EditText = findViewById(R.id.enterusername)
        val pw: EditText = findViewById(R.id.enterpassword)
        val cfpw: EditText = findViewById(R.id.confpassword)
        val adNum: EditText = findViewById(R.id.adnum)
        val adMoo: EditText = findViewById(R.id.ad1)
        val adSoi: EditText = findViewById(R.id.ad2)
        val adRoad: EditText = findViewById(R.id.ad3)
        val province: EditText = findViewById(R.id.enterProvince)
        val district: EditText = findViewById(R.id.enterDistrict)
        val tambon: EditText = findViewById(R.id.enterTambon)
        val adZipCode: EditText = findViewById(R.id.enterZipcode)

        val log: TextView = findViewById(R.id.toLog)
        log.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        btnRegis.setOnClickListener {
            if( name.text.trim().isEmpty() ||lastName.text.trim().isEmpty() || phone.text.trim().isEmpty()
                ||mail.text.trim().isEmpty() || username.text.trim().isEmpty() || pw.text.trim().isEmpty()
                || cfpw.text.trim().isEmpty() || adNum.text.trim().isEmpty() || adZipCode.text.trim().isEmpty()
                || province.text.trim().isEmpty() || district.text.trim().isEmpty() || tambon.text.trim().isEmpty()){
                if (name.text.toString().equals("")) {
                    name.setError("Please Enter First Name")
                }
                if (lastName.text.toString().equals("")) {
                    lastName.setError("Please Enter Last Name")
                }
                if (phone.text.toString().equals("")) {
                    phone.setError("Please Enter Your Phone Number")
                }
                if (username.text.toString().equals("")) {
                    username.setError("Please Enter UserName")
                }
                if (mail.text.toString().equals("")) {
                    mail.setError("Please Enter Email")
                }
                if (pw.text.toString().equals("")) {
                    pw.setError("Please Enter Password")
                }
                if (cfpw.text.toString().equals("")) {
                    cfpw.setError("Please Enter Confirm Password")
                }
                // Checking if confirm password is same
                if (!pw.text.toString().equals(cfpw.text.toString())) {
                    cfpw.setError("Password does not match")
                }
                if (adNum.text.toString().equals("")) {
                    adNum.setError("Please Enter Number")
                }
                if (province.text.toString().equals("")) {
                    province.setError("Please Enter Province")
                }
                if (district.text.toString().equals("")) {
                    district.setError("Please Enter District")
                }
                if (tambon.text.toString().equals("")) {
                    tambon.setError("Please Enter Tambon")
                }
                if (adZipCode.text.toString().equals("")) {
                    adZipCode.setError("Please EnterZipcode")
                }
            }
            if(name.text.trim().isNotEmpty() &&lastName.text.trim().isNotEmpty() && phone.text.trim().isNotEmpty()
                && mail.text.trim().isNotEmpty() && username.text.trim().isNotEmpty() && pw.text.trim().isNotEmpty()
                && cfpw.text.trim().isNotEmpty() && adNum.text.trim().isNotEmpty() && adZipCode.text.trim().isNotEmpty()
                && province.text.trim().isNotEmpty() && district.text.trim().isNotEmpty() && tambon.text.trim().isNotEmpty()
                && !cbRegis.isChecked )
            {
                Toast.makeText(this@register, "Need accept term", Toast.LENGTH_SHORT).show()

            }
            try {

                if (name.text.trim().isNotEmpty() && lastName.text.trim()
                        .isNotEmpty() && phone.text.trim().isNotEmpty()
                    && mail.text.trim().isNotEmpty() && username.text.trim()
                        .isNotEmpty() && pw.text.trim().isNotEmpty()
                    && cfpw.text.trim().isNotEmpty() && adNum.text.trim()
                        .isNotEmpty() && adZipCode.text.trim().isNotEmpty()
                    && province.text.trim().isNotEmpty() && district.text.trim()
                        .isNotEmpty() && tambon.text.trim().isNotEmpty()
                    && adMoo.text.trim().isNotEmpty() && adSoi.text.trim()
                        .isNotEmpty() && adRoad.text.trim().isNotEmpty()
                    && cbRegis.isChecked
                ) {

                    val apiRegister = API.retrofitBuild()
                    val call = apiRegister.postRegister(
                        postRegister(
                            username.text.toString(),
                            pw.text.toString(),
                            province.text.toString(),
                            district.text.toString(),
                            tambon.text.toString(),
                            name.text.toString(),
                            lastName.text.toString(),
                            phone.text.toString(),
                            adNum.text.toString(),
                            adZipCode.text.toString(),
                            adMoo.text.toString(),
                            adSoi.text.toString(),
                            adRoad.text.toString()
                        )
                    )
                    call.enqueue(object : Callback<List<callBackRegis>>{
                        override fun onResponse(
                            call: Call<List<callBackRegis>>,
                            response: Response<List<callBackRegis>>
                        ) {
                            if(response.isSuccessful)
                            {
                                if (response.isSuccessful) {
                                    val list: List<callBackRegis>? = response.body()
                                    var str: String = ""
                                    for (i: Int in 0 until list!!.size) {
                                        str = "${list[i].message}"
                                    }
                                    if(str.equals("success")) {
                                        Toast.makeText(this@register, "Success", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this@register, login::class.java)
                                        startActivity(intent)
                                    }
                                    else
                                    {
                                        Toast.makeText(this@register, "${str}", Toast.LENGTH_SHORT).show()
                                    }
                                } else {
                                    Toast.makeText(
                                        this@register,
                                        "Response  connection fail !! ",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                        }

                        override fun onFailure(call: Call<List<callBackRegis>>, t: Throwable) {
                            Log.e("inFailure","------------------------------------------")
                        }

                    })
                }
            }
            catch(e:Exception){
                Log.e("error",e.toString())
            }

        }

    }
}



