package com.example.week2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class Lk2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lk2)
        val button=findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val data=intent.getStringExtra("name1")
            AlertDialog.Builder(this).apply {
                setTitle("infomation from Lk1")
                setMessage(data)
                setPositiveButton("OK"){dialog,which->}
                show()
            }
        }
    }
}