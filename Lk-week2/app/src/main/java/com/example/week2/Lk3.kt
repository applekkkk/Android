package com.example.week2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Lk3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lk3)
        val back:Button=findViewById(R.id.back1)
        back.setOnClickListener {
            val intent=Intent(this,Lk1::class.java)
            val text: TextView =findViewById(R.id.edittext)
            val data = text.text.toString()
            intent.putExtra("3 to 1",data)
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}