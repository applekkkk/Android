package com.example.lk_week7

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.core.content.contentValuesOf

class AddDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)
        val insert: Button =findViewById(R.id.insert)
        insert.setOnClickListener {
            val identification = findViewById<EditText?>(R.id.identification).text.toString()
            val name = findViewById<EditText?>(R.id.name).text.toString()
            val age = findViewById<EditText?>(R.id.age).text.toString().toInt()
            val school = findViewById<EditText?>(R.id.school).text.toString()
            val phoneNumber = findViewById<EditText?>(R.id.phoneNumber).text.toString()
            val uri= Uri.parse("content://com.example.lk_week6.provider/Student")
            val values= contentValuesOf("id" to identification, "name" to name,"age" to age,"school" to school,"phoneNumber" to phoneNumber)
            Log.d("Add","click")
            contentResolver.insert(uri,values)
            Log.d("Add","insert")
            finish()
        }
    }
}