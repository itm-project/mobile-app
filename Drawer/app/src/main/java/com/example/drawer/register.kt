package com.example.drawer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_register.*
import java.lang.reflect.Array


class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val list1: MutableList<String> = ArrayList()
        list1.add("Tambon1")
        list1.add("Tambon2")
        list1.add("Tambon3")
        val adapter1: ArrayAdapter<String> =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list1)
        spinner1.adapter = adapter1

        val list2: MutableList<String> = ArrayList()
        list2.add("Amphoe1")
        list2.add("Amphoe2")
        list2.add("Amphoe3")
        val adapter2: ArrayAdapter<String> =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list2)
        spinner2.adapter = adapter2

        val list3: MutableList<String> = ArrayList()
        list3.add("Changwat1")
        list3.add("Changwat2")
        list3.add("Changwat3")
        val adapter3: ArrayAdapter<String> =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list3)
        spinner3.adapter = adapter3


        /*val op1 : Spinner = findViewById(R.id.spinner1)
        val spn1 : TextView = findViewById(R.id.spn1)
        val optn1 : kotlin.Array<String> = arrayOf("1","11","111")
        optn1.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,optn1)
        op1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                spn1.text = optn1.get(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //spn1.text = "SPN1"
            }
        }

        val op2 : Spinner = findViewById(R.id.spinner2)
        val spn2 : TextView = findViewById(R.id.spn2)
        val optn2 : kotlin.Array<String> = arrayOf("2","22","222")
        optn2.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,optn2)
        op2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                spn2.text = optn2.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //spn1.text = "SPN1"
            }

        }

        val op3 : Spinner = findViewById(R.id.spinner3)
        val spn3 : TextView = findViewById(R.id.spn3)
        val optn3 : kotlin.Array<String> = arrayOf("3","33","333")
        optn3.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,optn3)
        op3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                spn3.text = optn3.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //spn1.text = "SPN1"
            }

        }*/

        val log: TextView = findViewById(R.id.toLog)

        log.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        val btnRegis: Button = findViewById(R.id.btnRegis)
        val cbRegis: CheckBox = findViewById(R.id.cbTerms)
        val mail: EditText = findViewById(R.id.enteremail)
        val user: EditText = findViewById(R.id.enterusername)
        val pw: EditText = findViewById(R.id.enterpassword)
        val cfpw: EditText = findViewById(R.id.confpassword)
        val ad: EditText = findViewById(R.id.adnum)
        val ad1: EditText = findViewById(R.id.ad1)
        val ad2: EditText = findViewById(R.id.ad2)
        val ad3: EditText = findViewById(R.id.ad3)
        val ad4: EditText = findViewById(R.id.ad4)
        val spn1: Spinner = findViewById(R.id.spinner1)
        val spn2: Spinner = findViewById(R.id.spinner2)
        val spn3: Spinner = findViewById(R.id.spinner3)


        btnRegis.setOnClickListener {
            //cbRegis.setOnCheckedChangeListener { buttonView, isChecked ->

            if (mail.text.trim().isEmpty() || user.text.trim().isEmpty() || pw.text.trim()
                    .isEmpty() || cfpw.text.trim().isEmpty() || ad.text.trim().isEmpty()
                || ad1.text.trim().isEmpty() || ad2.text.trim().isEmpty() || ad3.text.trim()
                    .isEmpty() || ad4.text.trim().isEmpty()
            ) {
                Toast.makeText(this, "Input Required", Toast.LENGTH_SHORT).show()
            }
            if (mail.text.trim().isNotEmpty() && user.text.trim().isNotEmpty() && pw.text.trim()
                    .isNotEmpty() && cfpw.text.trim().isNotEmpty() && ad.text.trim()
                    .isNotEmpty() && ad1.text.trim().isNotEmpty() && ad2.text.trim()
                    .isNotEmpty() && ad3.text.trim().isNotEmpty() && ad4.text.trim()
                    .isNotEmpty()
            ) {
                Toast.makeText(this, "Need to Accept Terms", Toast.LENGTH_SHORT)
                    .show()
                cbRegis.setOnCheckedChangeListener { buttonView, isChecked ->
                    btnRegis.setOnClickListener {
                        if (!isChecked && mail.text.trim().isNotEmpty() && user.text.trim()
                                .isNotEmpty() && pw.text.trim()
                                .isNotEmpty() && cfpw.text.trim().isNotEmpty() && ad.text.trim()
                                .isNotEmpty()
                        ) {

                            Toast.makeText(this, "Need to Accept Terms", Toast.LENGTH_SHORT)
                                .show()

                        } else if (isChecked) {
                            if (mail.text.trim().isNotEmpty() && user.text.trim()
                                    .isNotEmpty() && pw.text.trim()
                                    .isNotEmpty() && cfpw.text.trim().isNotEmpty() && ad.text.trim()
                                    .isNotEmpty() && ad1.text.trim().isNotEmpty() && ad2.text.trim()
                                    .isNotEmpty() && ad3.text.trim().isNotEmpty() && ad4.text.trim()
                                    .isNotEmpty()
                            ) {
                                Toast.makeText(this, "Regis!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            } else
                                Toast.makeText(this, "Input Required", Toast.LENGTH_SHORT).show()


                        }
                    }
                }


            }


        }


    }


}
