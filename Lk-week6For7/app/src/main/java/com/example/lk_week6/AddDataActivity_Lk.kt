package com.example.lk_week6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddDataActivity_Lk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)
        val db=StudentsDatabaseHelper_Lk(this,"School.db",1).writableDatabase
        val insert:Button=findViewById(R.id.insert)
        insert.setOnClickListener {
            val identification=findViewById<EditText?>(R.id.identification).text.toString()
            val name=findViewById<EditText?>(R.id.name).text.toString()
            val age=findViewById<EditText?>(R.id.age).text.toString()
            val school=findViewById<EditText?>(R.id.school).text.toString()
            val phoneNumber=findViewById<EditText?>(R.id.phoneNumber).text.toString()
            db.execSQL("insert into Student values(" +
                    "?," +
                    "?," +
                    "?," +
                    "?," +
                    "?)",
                arrayOf(identification,name,school,age,phoneNumber))
            finish()
        }
    }
}