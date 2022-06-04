package com.example.tours_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tours_android.viewmodels.ExampleItem

class ExampleAdapter(private val exampleList:List<ExampleItem>) :RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {


    /**
     * @param parent is the ViewGroup that the View will be added to
     * @viewType is the type of View that will be created
     * @returns a new ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_card_view,parent,false)
     return ExampleViewHolder(itemView)
    }
    //this method is called when the recycler view needs a new view holder to represent an item
    //this is called each time when we scroll to a new position
    /**
     * @param holder is the view holder that will be reused
     * @param position is the position of the item in the list
     */

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
//        print("onBindViewHolder")
//        System.out.println("onBindViewHolder")
        val currentItem=exampleList[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text=currentItem.text1
        holder.textView2.text=currentItem.text2
    }

    override fun getItemCount()= exampleList.size

    //viewholder represents a single row in the recycler view
    /**
     * @param itemView is the view that will be used to represent a single row in the recycler view
     * @return the view holder that will be used to represent a single row in the recycler view
     * */
    class ExampleViewHolder (itemView:View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView=itemView.findViewById(R.id.image_view)
        val textView1:TextView=itemView.findViewById(R.id.text_view1)
        val textView2:TextView=itemView.findViewById(R.id.text_view2)


    }
}