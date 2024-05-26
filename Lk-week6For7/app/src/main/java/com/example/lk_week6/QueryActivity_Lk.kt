package com.example.lk_week6

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView

class QueryActivity_Lk : AppCompatActivity() {
    private lateinit var db:SQLiteDatabase
    private lateinit var adapter:ArrayAdapter<Student>
    private val studentList=ArrayList<Student>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query)
        db=StudentsDatabaseHelper_Lk(this,"School.db",1).writableDatabase
        adapter=StudentsAdapter(this,R.layout.listview_students,studentList)
        val listView:ListView=findViewById(R.id.listStudent)
        listView.adapter=adapter
        listView.setOnItemClickListener() { parent,view,position,id ->
            val student =studentList[position]
            val intent=Intent(this,AlterActivity_Lk::class.java)
            intent.putExtra("name",student.name)
            intent.putExtra("id",student.id)
            intent.putExtra("school",student.school)
            intent.putExtra("age",student.age)
            intent.putExtra("phoneNumber",student.phoneNumber)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        initStudents()
        adapter.notifyDataSetChanged()

    }
    private fun initStudents(){
        val cursor=db.rawQuery("select * from Student",null)
        studentList.clear()
        if(cursor.moveToFirst()){
            do {
                val name=cursor.getString(cursor.getColumnIndex("name"))
                val id=cursor.getString(cursor.getColumnIndex("id"))
                val school=cursor.getString(cursor.getColumnIndex("school"))
                val age=cursor.getInt(cursor.getColumnIndex("age"))
                val phoneNumber=cursor.getString(cursor.getColumnIndex("phoneNumber"))
                studentList.add(Student(id,name,school,age,phoneNumber))
            }while (cursor.moveToNext())
            cursor.close()
        }
    }

}