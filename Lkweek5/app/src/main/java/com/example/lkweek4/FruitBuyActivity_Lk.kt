package com.example.lkweek4

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class FruitBuyActivity_Lk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_buy)
        val image=intent.getIntExtra("fruitId",0)
        val name=intent.getStringExtra("fruitName")
        val unitPrice=intent.getIntExtra("fruitPrice",0)
        val fragment:Fragment?=supportFragmentManager.findFragmentById(R.id.FruitBuyFrag)
        if (fragment is FruitBuyFragment_Lk){
            if (name != null) {
                fragment.refresh(name,image,unitPrice)
            }
        }
    }
    companion object{
        fun action_start(context:Context,fruit:FruitIntroduce){
            val intent=Intent(context,FruitBuyActivity_Lk::class.java).apply {
                putExtra("fruitId",fruit.imageId)
                putExtra("fruitName",fruit.name)
                putExtra("fruitPrice",fruit.unitPrice)
            }
            context.startActivity(intent)
        }
    }
}