package com.example.week2

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import org.w3c.dom.Text

class Lk1 : AppCompatActivity() {
    private val requestDataLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result->
        if(result.resultCode== RESULT_OK){
            val data=result.data?.getStringExtra("3 to 1")
            Toast.makeText(this,data,Toast.LENGTH_LONG).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val enter2=findViewById<Button>(R.id.enter2)
        enter2.setOnClickListener {
            val intent=Intent(this,Lk2::class.java)
            val text=findViewById<TextView>(R.id.text)
            intent.putExtra("name1",text.text.toString())
            Log.d(javaClass.simpleName,text.text.toString())
            startActivity(intent)
        }
        val enter3=findViewById<Button>(R.id.enter3)
        enter3.setOnClickListener {
            val intent=Intent("action3")
            intent.addCategory("category3")
            requestDataLauncher.launch(intent)
        }

    }


}