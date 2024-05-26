package com.example.lk_week3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import java.lang.IllegalArgumentException
class Msg(val content:String,val type:Int,val avatarId:Int){
    companion object{
        const val RECEIVED=0
        const val SENT=1
    }
}
class MsgAdapter(val msgList: List<Msg>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class leftMsgViewHolder(view: View):RecyclerView.ViewHolder(view){
        val leftMsg:TextView=view.findViewById(R.id.leftMsg)
        val avatar:CircleImageView=view.findViewById(R.id.avatar)
    }
    inner class rightMsgViewHolder(view:View):RecyclerView.ViewHolder(view){
        val rightMsg:TextView=view.findViewById(R.id.rightMsg)
        val avatar:CircleImageView=view.findViewById(R.id.avatar)

    }

    override fun getItemViewType(position: Int): Int {
        val msg=msgList[position]
        return msg.type
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==Msg.RECEIVED){
            val view=LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item,parent,false)
            return leftMsgViewHolder(view)
        }else{
            val view=LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item,parent,false)
            return rightMsgViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return msgList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg=msgList[position]
        when(holder){
            is leftMsgViewHolder -> {
                holder.leftMsg.text = msg.content
                holder.avatar.setImageResource(msg.avatarId)
            }
            is rightMsgViewHolder -> {
                holder.rightMsg.text = msg.content
                holder.avatar.setImageResource(msg.avatarId)
            }
            else ->throw IllegalArgumentException()
        }
    }

}