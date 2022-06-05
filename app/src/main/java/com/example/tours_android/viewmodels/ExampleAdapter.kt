package com.example.tours_android.viewmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tours_android.R
import com.example.tours_android.model.Movie

class ExampleAdapter(private val exampleList:List<ExampleItem>,
    private val listener: OnItemClickListener
                     )
    :RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

var items= mutableListOf<Movie>()
    fun setList(items: List<Movie>){
        this.items=items.toMutableList()
        notifyDataSetChanged()
    }
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

        holder.bind(items.get(position))
        val currentItem=exampleList[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text=currentItem.text1
        holder.textView2.text=currentItem.text2
    }

    override fun getItemCount()= items.size

    //viewholder represents a single row in the recycler view
    /**
     * @param itemView is the view that will be used to represent a single row in the recycler view
     * @return the view holder that will be used to represent a single row in the recycler view
     * */
   inner class ExampleViewHolder (itemView:View): RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val imageView: ImageView=itemView.findViewById(R.id.image_view)
        val textView1:TextView=itemView.findViewById(R.id.text_view1)
        val textView2:TextView=itemView.findViewById(R.id.text_view2)

        fun bind(item: Movie){
           // imageView.setImageResource(item.imageUrl)
            textView1.text=item.name
            textView2.text=item.description
        }

        init {
            itemView.setOnClickListener (this)
        }

        override fun onClick(v: View?) {
            if(adapterPosition!=RecyclerView.NO_POSITION){
                listener.onItemClick(adapterPosition)
            }

        }


    }

    interface OnItemClickListener{
        fun onItemClick(position:Int)
    }

}