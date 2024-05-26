package com.example.lk_week3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class dialogActivitylk : AppCompatActivity() {
    private val dialogList=ArrayList<Dialog_item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        initDialogs()
        val layoutManager=LinearLayoutManager(this)
        val recyclerView:RecyclerView=findViewById(R.id.dialog)
        recyclerView.layoutManager=layoutManager
        val adapter=DialogAdapter(dialogList)
        recyclerView.adapter=adapter
    }
    private fun initDialogs() {
        repeat(3) {
            dialogList.add(Dialog_item(R.drawable.work, "work", "I am work"))
            dialogList.add(Dialog_item(R.drawable.mute, "mute", "I am mute"))
            dialogList.add(Dialog_item(R.drawable.setting,"setting","I am setting"))
            dialogList.add(Dialog_item(R.drawable.photo,"photo","I am photo"))
        }
    }
}