package com.example.drawer

import com.google.gson.annotations.SerializedName

data class newsData(@SerializedName("news_id") val news_id:Int, @SerializedName("name") val name:String, @SerializedName("detail") val detail:String, @SerializedName("date") val date:Int, @SerializedName("notification_id") val notification_id:Int) {
}