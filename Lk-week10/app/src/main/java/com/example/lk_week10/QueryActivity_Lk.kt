package com.example.lk_week10

import android.app.Activity
import android.app.DownloadManager.Query
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.StringReader
import kotlin.concurrent.thread


class QueryActivity_Lk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query)
        QueryStudents(this)
    }
    override fun onResume() {
        super.onResume()
        QueryStudents(this)

    }
    private fun QueryStudents(activity: Activity){
        HttpUtil.QueryStudent("http://2452x7g449.wicp.vip:9998/Service1.asmx/QueryStudents",object :Callback{
            val studentList=ArrayList<Student>()
            override fun onResponse(call: Call, response: Response) {
                val response=response.body?.string()
                runOnUiThread {
                    if (response != null) {
                        showData(response)
                        val listView:ListView=findViewById(R.id.listStudent)
                        listView.adapter=StudentsAdapter(activity,R.layout.listview_students,studentList)
                        listView.setOnItemClickListener() { parent,view,position,id ->
                            val student =studentList[position]
                            val intent= Intent(activity,AlterActivity_Lk::class.java)
                            intent.putExtra("name",student.name)
                            intent.putExtra("id",student.id)
                            intent.putExtra("school",student.school)
                            intent.putExtra("age",student.age)
                            intent.putExtra("phoneNumber",student.phoneNumber)
                            startActivity(intent)
                        }
                    }
                }
            }
            private fun showData(response:String){
                try {
                    val factory=XmlPullParserFactory.newInstance()
                    val xmlPullParser=factory.newPullParser()
                    xmlPullParser.setInput(StringReader(response))
                    var eventType=xmlPullParser.eventType
                    var id=""
                    var name=""
                    var school=""
                    var age=""
                    var phone=""
                    while (eventType!=XmlPullParser.END_DOCUMENT){
                        val nodeName=xmlPullParser.name
                        Log.d("Main","nodeName is $nodeName")
                        when(eventType){
                            XmlPullParser.START_TAG->{
                                when(nodeName){
                                    "id"->id=xmlPullParser.nextText()
                                    "name"->name=xmlPullParser.nextText()
                                    "dept"->school=xmlPullParser.nextText()
                                    "age"->age=xmlPullParser.nextText()
                                    "phone"->phone=xmlPullParser.nextText()
                                }

                            }
                            XmlPullParser.END_TAG->{
                                if("Table"==nodeName){
                                    studentList.add(Student(id,name,school,age.toInt(),phone))
                                }
                            }
                        }
                        eventType=xmlPullParser.next()
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
        })
    }


}

