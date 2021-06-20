package com.erdemzengin.hyggeapp.adapters.vpAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erdemzengin.hyggeapp.R
import com.erdemzengin.hyggeapp.models.main.CategoryItem



class MainViewPagerAdapter(val categoryItemList:ArrayList<CategoryItem>, val listener:Listener): RecyclerView.Adapter<MainViewPagerAdapter.ViewPagerViewHolder>() {

    interface Listener {
        fun onItemClickedViewPager(categoryItem:CategoryItem)
    }
    class ViewPagerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val viewPagerIv: ImageView = itemView.findViewById(R.id.viewPagerImageView)

        fun bindItems(categoryItem: CategoryItem,listener: Listener){


            Glide.with(itemView.context)
                    .load(categoryItem.imageUrl)
                    .into(viewPagerIv)

            itemView.setOnClickListener {
                listener.onItemClickedViewPager(categoryItem)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.viewpager_cardview, parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bindItems(categoryItemList[position],listener)
    }

    override fun getItemCount(): Int {
        return categoryItemList.size

    }

    fun updateData(list: List<CategoryItem>) {
        categoryItemList.clear()
        categoryItemList.addAll(list)
        notifyDataSetChanged()
    }
}