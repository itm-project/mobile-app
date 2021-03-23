package com.example.drawer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class VIewPagerAdapter (private val title : List<String>,private val detail : List<String>,private val images : List<Int>) : RecyclerView.Adapter<VIewPagerAdapter.Pager2ViewHolder>(){

    inner class Pager2ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val itemTitle : TextView = itemView.findViewById(R.id.tvTitle)
        val itemDetails : TextView = itemView.findViewById(R.id.tvAbout)
        val itemImage : ImageView = itemView.findViewById(R.id.imView)

        init {
            itemImage.setOnClickListener { v:View ->
                val pos = adapterPosition
                Toast.makeText(itemView.context,"item #${pos+1} clicked",Toast.LENGTH_SHORT).show()

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VIewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page,parent,false))
   }

    override fun getItemCount(): Int {
        return title.size
    }

    override fun onBindViewHolder(holder: VIewPagerAdapter.Pager2ViewHolder, position: Int) {
        holder.itemTitle.text = title[position]
        holder.itemDetails.text = detail[position]
        holder.itemImage.setImageResource(images[position])
    }
}


