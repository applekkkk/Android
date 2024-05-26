package com.example.lkweek4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FruitBuyFragment_Lk:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fruit_buy,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val unit_price:TextView?=activity?.findViewById(R.id.unitPrice)
        val subtract:Button?=activity?.findViewById(R.id.subtract)
        val add:Button?=activity?.findViewById(R.id.add)
        val amount:TextView?=activity?.findViewById(R.id.amount)
        val totalPrice:TextView?=activity?.findViewById(R.id.totalPrice)
        val order:Button?=activity?.findViewById(R.id.order)
        subtract?.setOnClickListener {
            if(amount?.text.toString().toInt()==0){
                Toast.makeText(activity,"already zero",Toast.LENGTH_LONG).show()
            }
            else{
                val amount1=amount?.text.toString().toInt()-1
                amount?.text=amount1.toString()
                val unitPrice1=unit_price?.text.toString().dropLast(3).substring(1).toInt()
                totalPrice?.text="$"+ (amount1*unitPrice1).toString()
            }
        }

        add?.setOnClickListener {
            val amount1=amount?.text.toString().toInt()+1
            val unitPrice1=unit_price?.text.toString().dropLast(3).substring(1).toInt()
            amount?.text=amount1.toString()
            totalPrice?.text= "$"+(amount1*unitPrice1).toString()
        }

        order?.setOnClickListener {
            Toast.makeText(activity,"order success!",Toast.LENGTH_LONG).show()
        }
    }

    fun refresh(fname:String,fimage:Int,funitPrice:Int){
        val unit_price:TextView?=activity?.findViewById(R.id.unitPrice)
        val name:TextView?=activity?.findViewById(R.id.name)
        val image:ImageView?=activity?.findViewById(R.id.image)
        val amount:TextView?=activity?.findViewById(R.id.amount)
        val totalPrice:TextView?=activity?.findViewById(R.id.totalPrice)
        name?.text=fname
        image?.setImageResource(fimage)
        unit_price?.text="$"+funitPrice.toString()+"/kg"
        amount?.text="1"
        totalPrice?.text="$"+funitPrice.toString()
        view?.visibility=View.VISIBLE
    }
}