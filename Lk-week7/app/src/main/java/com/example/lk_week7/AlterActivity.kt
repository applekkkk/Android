package com.example.lk_week7

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.contentValuesOf

class AlterActivity : AppCompatActivity() {
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
            val uri= Uri.parse("content://com.example.lk_week6.provider/Student")
            val values= contentValuesOf("name" to name.text.toString(),"school" to school.text.toString(),"age" to age.text.toString(),"phoneNumber" to phoneNumber.text.toString())
            contentResolver.update(uri,values,"id=?", arrayOf(id.text.toString()))
            finish()
        }
        delete.setOnClickListener {
            val uri= Uri.parse("content://com.example.lk_week6.provider/Student")
            contentResolver.delete(uri,"id=?", arrayOf(id.text.toString()))
            finish()

        }
    }
}