package com.finalProject.myapplication.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.finalProject.myapplication.Models.Product
import com.finalProject.myapplication.R
import de.hdodenhof.circleimageview.CircleImageView

class ProfileDataAdapter(var myList : ArrayList<Product>, var context: Context) : RecyclerView.Adapter<ProfileDataAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var productImageView : CircleImageView = itemView.findViewById(R.id.product_item_image)
        var productName : TextView = itemView.findViewById(R.id.product_item_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val myData = myList.get(position)
        Glide.with(View(context)).load(myData.image).placeholder(R.drawable.ic_launcher_background).into(holder.productImageView)
        holder.productName.text = myData.name
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}