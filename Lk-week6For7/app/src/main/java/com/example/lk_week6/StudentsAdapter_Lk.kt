package com.example.lk_week6

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class Student(val id:String,val name:String,val school:String,val age:Int,val phoneNumber:String)
class StudentsAdapter(activity: Activity,val resouceId:Int,data:List<Student>):ArrayAdapter<Student>(activity,resouceId,data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view=LayoutInflater.from(context).inflate(resouceId,parent,false)
        val id:TextView=view.findViewById(R.id.id1)
        val name:TextView=view.findViewById(R.id.name1)
        val school:TextView=view.findViewById(R.id.school1)
        val student=getItem(position)
        if(student!=null){
            id.setText(student.id)
            name.setText(student.name)
            school.setText(student.school)
        }
        return view
    }
}