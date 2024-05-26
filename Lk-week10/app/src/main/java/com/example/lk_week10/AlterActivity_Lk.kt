package com.example.lk_week10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class AlterActivity_Lk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alter)
        val id: TextView =findViewById(R.id.id2)
        val name: EditText =findViewById(R.id.name2)
        val school: EditText =findViewById(R.id.school2)
        val age: EditText =findViewById(R.id.age2)
        val phoneNumber: EditText =findViewById(R.id.phoneNumber2)
        val alter: Button =findViewById(R.id.alter)
        val delete: Button =findViewById(R.id.delete)
        id.setText(intent.getStringExtra("id"))
        name.setText(intent.getStringExtra("name"))
        school.setText(intent.getStringExtra("school"))
        age.setText(intent.getIntExtra("age",0).toString())
        phoneNumber.setText(intent.getStringExtra("phoneNumber"))
        alter.setOnClickListener {
            updateStudent(id.text.toString().toInt(),name.text.toString(),school.text.toString(),age.text.toString().toInt(),phoneNumber.text.toString())
        }
        delete.setOnClickListener {
            deleteStudent(id.text.toString().toInt())
        }
    }
    private fun deleteStudent(id:Int){
        val client=OkHttpClient()
        val requestBody=FormBody.Builder()
            .add("id",id.toString())
            .build()
        val request=Request.Builder()
            .url("http://2452x7g449.wicp.vip:9998/Service1.asmx/deleteStudent")
            .post(requestBody)
            .build()
        thread {
            val response=client.newCall(request).execute()
            finish()
        }
    }
    private fun updateStudent(id:Int,name: String,school:String,age:Int,phone:String){
        val client=OkHttpClient()
        val requestBody=FormBody.Builder()
            .add("id",id.toString())
            .add("name",name)
            .add("dept",school)
            .add("age",age.toString())
            .add("phone",phone)
            .build()
        val request=Request.Builder()
            .url("http://2452x7g449.wicp.vip:9998/Service1.asmx/updateStudent")
            .post(requestBody)
            .build()
        thread {
            val response=client.newCall(request).execute()
            finish()
        }
    }
}