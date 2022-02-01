package com.example.simplletodo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


/*
  // it's bridge of the recyclerViwer to display the dat we give it.
 */

class TaskToDo(val listOfItems: List<String>,val longClickListener:OnLongClickListener): RecyclerView.Adapter<TaskToDo.ViewHolder>() {

interface OnLongClickListener{
    fun onItemLongClicked(position: Int)

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
       val item=listOfItems.get(position)
        holder.textview.text=item
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
          val textview: TextView
          init{
              textview=itemView.findViewById(android.R.id.text1)

          itemView.setOnClickListener{
             longClickListener.onItemLongClicked(adapterPosition)
              true
          }


          }
    }



}