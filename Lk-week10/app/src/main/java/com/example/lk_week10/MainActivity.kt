package com.example.lk_week10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val AddBtn:Button=findViewById(R.id.AddBtn)
        val QueryBtn:Button=findViewById(R.id.QueryBtn)
        AddBtn.setOnClickListener {
            val intent=Intent(this,AddDataActivity_Lk::class.java)
            startActivity(intent)
        }
        QueryBtn.setOnClickListener {
            val intent=Intent(this,QueryActivity_Lk::class.java)
            startActivity(intent)
        }
    }
}