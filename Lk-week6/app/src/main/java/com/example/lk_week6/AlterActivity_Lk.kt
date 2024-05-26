package com.example.lk_week6

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class AlterActivity_Lk : AppCompatActivity() {
    lateinit var db:SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alter)
        db=StudentsDatabaseHelper_Lk(this,"School.db",1).writableDatabase
        val id:TextView=findViewById(R.id.id2)
        val name:EditText=findViewById(R.id.name2)
        val school:EditText=findViewById(R.id.school2)
        val age:EditText=findViewById(R.id.age2)
        val phoneNumber:EditText=findViewById(R.id.phoneNumber2)
        val alter:Button=findViewById(R.id.alter)
        val delete:Button=findViewById(R.id.delete)
        id.setText(intent.getStringExtra("id"))
        name.setText(intent.getStringExtra("name"))
        school.setText(intent.getStringExtra("school"))
        age.setText(intent.getIntExtra("age",0).toString())
        phoneNumber.setText(intent.getStringExtra("phoneNumber"))
        alter.setOnClickListener {
            db.execSQL("update Student " +
                    "set name=?,school=?,age=?,phoneNumber=? " +
                    "where id=?", arrayOf(name.text.toString(),school.text.toString(),age.text.toString().toInt(),phoneNumber.text.toString(),id.text.toString()))
            finish()
        }
        delete.setOnClickListener {
            db.execSQL("delete from Student " +
                    "where id=?", arrayOf(id.text.toString()))
            finish()
        }
    }
}