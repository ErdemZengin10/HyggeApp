package com.erdemzengin.hyggeapp.adapters.vpAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erdemzengin.hyggeapp.R
import kotlinx.android.synthetic.main.viewpager_cardview_detail.view.*

class DetailViewPagerAdapter(val imageList:ArrayList<String>):RecyclerView.Adapter<DetailViewPagerAdapter.DetailViewPagerViewHolder>() {

    class DetailViewPagerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val viewPagerIv: ImageView = itemView.findViewById(R.id.viewPagerImageViewDetail)

        fun bindIv(image: String){
            Glide.with(itemView.context)
                .load(image)
                .into(viewPagerIv)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewPagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.viewpager_cardview_detail, parent,false)
        return DetailViewPagerAdapter.DetailViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewPagerViewHolder, position: Int) {
        holder.bindIv(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
    fun updateData(list:List<String>){
        imageList.clear()
        imageList.addAll(list)
        notifyDataSetChanged()
    }
}