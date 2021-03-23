package com.example.drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentTransaction

class EditAllSetting : AppCompatActivity() {

    lateinit var setFrag : SettingFragment
    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_all_setting)

        val btn : Button = findViewById(R.id.btnBackSetting)
        val ed : EditText = findViewById(R.id.EditStUsername)
        //val tv : TextView = findViewById(R.id.tv0)
        btn.setOnClickListener {

            /*val f : FragmentTransaction = manager.beginTransaction()
            f.replace(R.id.ActEditSetting,SettingFragment()).commit()*/

            /*val st:String = ed.text.toString()
            tv.setText(st)*/

            /*setFrag = SettingFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, setFrag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()*/
        }
    }
}