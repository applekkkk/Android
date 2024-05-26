package com.example.lk_week10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class AddDataActivity_Lk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)
        val insert:Button=findViewById(R.id.insert)
        insert.setOnClickListener {
            val name=findViewById<EditText?>(R.id.name).text.toString()
            val age=findViewById<EditText?>(R.id.age).text.toString()
            val school=findViewById<EditText?>(R.id.school).text.toString()
            val phoneNumber=findViewById<EditText?>(R.id.phoneNumber).text.toString()
            val client= OkHttpClient()
            val requestBody= FormBody.Builder()
                .add("name",name)
                .add("dept",school)
                .add("age",age)
                .add("phone",phoneNumber)
                .build()
            val request= Request.Builder()
                .url("http://2452x7g449.wicp.vip:9998/Service1.asmx/insertStudent")
                .post(requestBody)
                .build()
            thread {
                val response=client.newCall(request).execute()
                finish()
            }
        }
    }
}