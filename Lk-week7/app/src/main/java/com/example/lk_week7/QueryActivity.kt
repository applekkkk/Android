package com.example.lk_week7

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class QueryActivity : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<Student>
    private val studentList=ArrayList<Student>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query)
        adapter=StudentsAdapter(this,R.layout.listview_students,studentList)
        val listView: ListView =findViewById(R.id.listStudent)
        listView.adapter=adapter
        listView.setOnItemClickListener() { parent,view,position,id ->
            val student =studentList[position]
            val intent= Intent(this,AlterActivity::class.java)
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
        studentList.clear()
        val uri= Uri.parse("content://com.example.lk_week6.provider/Student")
        contentResolver.query(uri,null,null,null,null)?.apply {
            while (moveToNext()){
                val id=getString(getColumnIndex("id"))
                val name=getString(getColumnIndex("name"))
                val school=getString(getColumnIndex("school"))
                val age=getInt(getColumnIndex("age"))
                val phoneNumber=getString(getColumnIndex("phoneNumber"))
                studentList.add(Student(id,name,school,age,phoneNumber))
            }
        }
    }
}