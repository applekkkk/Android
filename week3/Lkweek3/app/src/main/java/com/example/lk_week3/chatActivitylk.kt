package com.example.lk_week3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class chatActivitylk : AppCompatActivity(), View.OnClickListener {
    private val MsgList=ArrayList<Msg>()
    private var adapter: MsgAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initMsg()
        val layoutManager=LinearLayoutManager(this)
        val recyclerView:RecyclerView=findViewById(R.id.recyclerview1)
        recyclerView.layoutManager=layoutManager
        adapter= MsgAdapter(MsgList)
        recyclerView.adapter=adapter
        val send:Button=findViewById(R.id.send)
        send.setOnClickListener(this)
    }
    fun initMsg(){
        val avatar=intent.getStringExtra("avatar")
        val text=intent.getStringExtra("msg")
        val msg1= avatar?.let { Msg(text.toString(),Msg.RECEIVED, it.toInt()) }
        if (msg1 != null) {
            MsgList.add(msg1)
        }
    }

    override fun onClick(v: View?) {
        val send:Button=findViewById(R.id.send)
        val input:EditText=findViewById(R.id.input)
        when(v){
            send->{
                val content=input.text.toString()
                if(content.isNotEmpty()){
                    val msg=Msg(content,Msg.SENT,R.drawable.mh)
                    MsgList.add(msg)
                    adapter?.notifyItemInserted(MsgList.size-1)
                    val recyclerView:RecyclerView=findViewById(R.id.recyclerview1)
                    recyclerView.scrollToPosition(MsgList.size-1)
                    input.setText("")
                }
            }
        }
    }
}

