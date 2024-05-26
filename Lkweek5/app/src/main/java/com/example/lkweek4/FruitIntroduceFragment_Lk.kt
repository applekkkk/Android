package com.example.lkweek4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FruitIntroduceFragment:Fragment() {

    var istablet=false
    val fruitIntroduceList=ArrayList<FruitIntroduce>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Introduce","Create")
        return inflater.inflate(R.layout.fragment_fruit_introduce,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initfruit()
        val layoutManager= LinearLayoutManager(activity)
        val recyclerView: RecyclerView? =activity?.findViewById(R.id.fruitTittleRecyclerview)
        recyclerView?.layoutManager=layoutManager
        recyclerView?.adapter=FruitIntroduceAdapter(fruitIntroduceList)
        istablet=activity?.findViewById<View>(R.id.FruitBuyFrag_tablet)!=null
    }

    fun initfruit(){
        repeat(3){
            fruitIntroduceList.add(FruitIntroduce("apple",R.drawable.apple_pic,2))
            fruitIntroduceList.add(FruitIntroduce("Banana",R.drawable.banana_pic,3))
            fruitIntroduceList.add(FruitIntroduce("Orange",R.drawable.orange_pic,2))
            fruitIntroduceList.add(FruitIntroduce("Watermelon",R.drawable.watermelon_pic,3))
            fruitIntroduceList.add(FruitIntroduce("Pear",R.drawable.pear_pic,5))
            fruitIntroduceList.add(FruitIntroduce("Grape",R.drawable.grape_pic,4))
            fruitIntroduceList.add(FruitIntroduce("Pineapple",R.drawable.pineapple_pic,5))
            fruitIntroduceList.add(FruitIntroduce("Strawberry",R.drawable.strawberry_pic,6))

        }
    }

    inner class FruitIntroduceAdapter(val fruitIntroduceList: List<FruitIntroduce>):RecyclerView.Adapter<FruitIntroduceAdapter.ViewHolder>(){
        inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
            val name: TextView =view.findViewById(R.id.name1)
            val imageId: ImageView =view.findViewById(R.id.image1)
            val unitPrice: TextView =view.findViewById(R.id.unitPrice1)
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitIntroduceAdapter.ViewHolder {
            val view=LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_fruit_introduce,parent,false)
            val holder=ViewHolder(view)
            holder.itemView.setOnClickListener{
                if(!istablet) {
                    val fruit = fruitIntroduceList[holder.adapterPosition]
                    FruitBuyActivity_Lk.action_start(parent.context, fruit)
                }else{
                    val fruit = fruitIntroduceList[holder.adapterPosition]
                    activity?.apply{
                        val fragment=supportFragmentManager.findFragmentById(R.id.FruitBuyFrag_tablet) as FruitBuyFragment_Lk
                        fragment.refresh(fruit.name,fruit.imageId,fruit.unitPrice)
                    }
                }
            }
            return holder
        }

        override fun getItemCount(): Int {
            return fruitIntroduceList.size
        }

        override fun onBindViewHolder(holder:FruitIntroduceAdapter.ViewHolder, position: Int) {
            val fruitIntroduction=fruitIntroduceList[position]
            holder.name.text=fruitIntroduction.name
            holder.imageId.setImageResource(fruitIntroduction.imageId)
            holder.unitPrice.text="$"+fruitIntroduction.unitPrice+"/kg"
        }


    }
}
class FruitIntroduce(val name:String,val imageId:Int,val unitPrice:Int)


