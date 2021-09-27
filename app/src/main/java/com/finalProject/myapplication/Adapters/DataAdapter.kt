package com.finalProject.myapplication.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.finalProject.myapplication.ClickableItem
import com.finalProject.myapplication.Models.DataModel
import com.finalProject.myapplication.R
import de.hdodenhof.circleimageview.CircleImageView

class DataAdapter(var myList : DataModel,var context: Context,var cli:ClickableItem ) : RecyclerView.Adapter<DataAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View , var cl : ClickableItem) : RecyclerView.ViewHolder(itemView){
        var imageView : CircleImageView = itemView.findViewById(R.id.my_image)
        var direction : ImageView = itemView.findViewById(R.id.direction)
        var name : TextView = itemView.findViewById(R.id.res_name)

        init {
            itemView.setOnClickListener {
                cl.getPosition(adapterPosition)
            }

            direction.setOnClickListener {
                cl.getDirection(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return MyViewHolder(view,cli)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val myData = myList.data.get(position)
        Glide.with(View(context)).load(myData.image).placeholder(R.drawable.ic_launcher_background).into(holder.imageView)
        holder.name.text = myData.name
    }

    override fun getItemCount(): Int {
        return myList.data.size
    }
}