package com.example.lk_week7

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val add: Button =findViewById(R.id.add)
        add.setOnClickListener {
            val intent= Intent(this,AddDataActivity::class.java)
            startActivity(intent)
        }
        val query: Button =findViewById(R.id.query)
        query.setOnClickListener {
            val intent= Intent(this,QueryActivity::class.java)
            startActivity(intent)
        }

    }
}