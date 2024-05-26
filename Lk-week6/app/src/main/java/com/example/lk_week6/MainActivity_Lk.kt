package com.example.lk_week6

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity_Lk : AppCompatActivity() {
    lateinit var db:SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val create:Button=findViewById(R.id.create)
        val dbHelper=StudentsDatabaseHelper_Lk(this,"School.db",1)
        create.setOnClickListener {
           db=dbHelper.writableDatabase
        }
        val add:Button=findViewById(R.id.add)
        add.setOnClickListener {
            val intent=Intent(this,AddDataActivity_Lk::class.java)
            startActivity(intent)
        }
        val query:Button=findViewById(R.id.query)
        query.setOnClickListener {
            val intent=Intent(this,QueryActivity_Lk::class.java)
            startActivity(intent)
        }
        val init:Button=findViewById(R.id.init)
        init.setOnClickListener {
            db.execSQL("Drop table if exists Student")
            dbHelper.onCreate(db)
            db.execSQL("insert into Student values('123456','张三','信息学院',21,'28008303')," +
                    "('123457','李四','信息学院',21,'28008303')," +
                    "('123458','王五','信电学院',21,'28008303')," +
                    "('123478','赵六','信电学院',21,'28008303')")
        }
    }
}