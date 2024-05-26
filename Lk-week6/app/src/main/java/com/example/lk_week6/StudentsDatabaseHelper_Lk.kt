package com.example.lk_week6

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StudentsDatabaseHelper_Lk(val context: Context, name:String, version: Int):SQLiteOpenHelper(context,name,null,version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table Student(" +
                "id text primary key," +
                "name text," +
                "school text," +
                "age integer," +
                "phoneNumber text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}