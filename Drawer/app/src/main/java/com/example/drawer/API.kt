package com.example.drawer

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


val BASE_URL = "http://158.108.213.144:5001/"
interface API {

    @GET("news")
    fun getNews(): Call<List<newsData>>

    @GET("notification/news")
    fun getNotiNews(): Call<List<notiData>>

    @GET("notification/important")
    fun getImNews(): Call<List<notiImData>>

    @Headers("Content-Type: application/json")
    @POST("login")
    fun doLogin(@Body loginPostData: LoginPostData): Call<List<userData>> // body data

    @Headers("Content-Type: application/json")
    @POST("user/profile")
    fun getProfile(@Body profileRequestData: profileRequestData) : Call<List<userData>>

    @Headers("Content-Type: application/json")
    @POST("address")
    fun getAddress(@Body addressRequestData: addressRequestData) : Call<List<addressData>>

    @Headers("Content-Type: application/json")
    @POST("history")
    fun getLocation(@Body historyRequestData : historyRequestData) : Call<List<getHistoryData>>

    @Headers("Content-Type:application/json")
    @POST("user/register")
    fun postRegister(@Body postRegister: postRegister):Call<List<callBackRegis>>


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