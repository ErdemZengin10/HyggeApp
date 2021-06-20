package com.erdemzengin.hyggeapp.adapters.rvAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erdemzengin.hyggeapp.R


class SizeRecyclerViewAdapter(val sizeList:ArrayList<String>, val listener: SizeRecyclerViewAdapter.Listener): RecyclerView.Adapter<SizeRecyclerViewAdapter.SizeViewHolder>() {


    class SizeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val sizeTextView:TextView=itemView.findViewById(R.id.sizeTextView)

        fun bind(size:String,listener: Listener){
            sizeTextView.text=size

            itemView.setOnClickListener{
                listener.onItemClicked(itemView)

            }
        }
    }

    interface Listener {
        fun onItemClicked(view: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.size_cardview, parent,false)
        return SizeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        holder.bind(sizeList[position], listener)




    }

    override fun getItemCount(): Int {
        return sizeList.size
    }
    fun updateData(list:List<String>){
        sizeList.clear()
        sizeList.addAll(list)
        notifyDataSetChanged()
    }
    fun resetBackground(){


    }


}