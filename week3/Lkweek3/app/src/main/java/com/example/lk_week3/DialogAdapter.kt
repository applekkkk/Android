package com.example.lk_week3

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class Dialog_item(val avatarId:Int, val name:String, val msg:String)
class DialogAdapter(val dialogList: List<Dialog_item>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val avatar:CircleImageView=view.findViewById(R.id.avatar)
        val name:TextView=view.findViewById(R.id.name)
        val msg:TextView=view.findViewById(R.id.msg)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.dialog_item,parent,false)
        val viewHolder=ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position=viewHolder.adapterPosition
            val dialog=dialogList[position]
            val intent=Intent(parent.context, chatActivitylk::class.java)
            intent.putExtra("avatar",dialog.avatarId.toString())
            intent.putExtra("name",dialog.name.toString())
            intent.putExtra("msg",dialog.msg.toString())
            parent.context.startActivity(intent)
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return dialogList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dialog=dialogList[position]
        if(holder is ViewHolder) {
            holder.avatar.setImageResource(dialog.avatarId)
            holder.name.text = dialog.name
            holder.msg.text = dialog.msg
        }
    }

}