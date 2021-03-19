package com.example.drawer

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

val BASE_URL = "http://158.108.213.142:5001/"
interface API {

    @GET("news")
    fun getNews(): Call<List<newsData>>


    companion object{
        fun retrofitBuild():API{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(API::class.java)
        }
    }
}