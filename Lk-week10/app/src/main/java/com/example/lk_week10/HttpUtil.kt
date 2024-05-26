package com.example.lk_week10

import okhttp3.OkHttpClient
import okhttp3.Request

object HttpUtil {
    fun QueryStudent(address:String,callback:okhttp3.Callback){
        val client=OkHttpClient()
        val request=Request.Builder()
            .url(address)
            .build()
        client.newCall(request).enqueue(callback)
    }
}