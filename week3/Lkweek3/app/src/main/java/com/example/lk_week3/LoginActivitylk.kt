package com.example.lk_week3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivitylk : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val login:Button=findViewById(R.id.login)
        val exit:Button=findViewById(R.id.exit)
        login.setOnClickListener(this)
        exit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val login:Button=findViewById(R.id.login)
        val exit:Button=findViewById(R.id.exit)
        val password:EditText=findViewById(R.id.password)
        when(v){
            login->{
                val strpassword=password.text.toString()
                if(strpassword=="qwe"){
                    val intent=Intent(this,dialogActivitylk::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"密码错误！",Toast.LENGTH_LONG).show()
                }
            }
            exit->{
                finish()
            }
        }
    }
}