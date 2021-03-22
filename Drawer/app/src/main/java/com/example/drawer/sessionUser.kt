package com.example.drawer

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

class sessionUser {
    lateinit var pref: SharedPreferences
    lateinit var editor:SharedPreferences.Editor
    lateinit var con:Context
    var PRIVATE_MODE:Int = 0

    constructor(con: Context){
        this.con = con
        pref = con.getSharedPreferences(PREF_NAME , PRIVATE_MODE)
        editor = pref.edit()
    }

    companion object{
        val PREF_NAME:String = "USER_DETAIL"
        val IS_LOGIN:String = "isLoggedIn"
        val KEY_ID:String = "user_id"
        val KEY_NAME:String = "name"
        val KEY_LASTNAME:String = "lastname"
    }

    fun createLoginSession(user_id:String, name:String , lastname:String)
    {
        editor.putBoolean(IS_LOGIN,true)
        editor.putString(KEY_ID , user_id)
        //editor.putInt(KEY_ID , user_id)
        editor.putString(KEY_NAME , name)
        editor.putString(KEY_LASTNAME , lastname)
        editor.commit()
    }

    fun checkLogin(){
        if(!this.isLoggedIn())
        {
            var i: Intent = Intent(con , MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            con.startActivity(i)
        }
    }

    fun getUserDetail():HashMap<String,String>{
        var user: Map<String,String> = HashMap<String,String>()
        (user as HashMap).put(KEY_ID, pref.getString(KEY_ID,null).toString())
        (user as HashMap).put(KEY_NAME, pref.getString(KEY_NAME,null).toString())
        (user as HashMap).put(KEY_LASTNAME, pref.getString(KEY_LASTNAME,null).toString())
        return user
    }

    fun LogoutUser()
    {
        editor.clear()
        editor.commit()

        var i: Intent = Intent(con , MainActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        con.startActivity(i)
    }

    fun isLoggedIn():Boolean{
        return pref.getBoolean(IS_LOGIN,false)
    }
}