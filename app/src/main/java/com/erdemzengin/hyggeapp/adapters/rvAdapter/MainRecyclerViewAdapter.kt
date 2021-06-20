package com.erdemzengin.hyggeapp.adapters.rvAdapter


import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erdemzengin.hyggeapp.R
import com.erdemzengin.hyggeapp.models.main.ProductItem
import kotlin.coroutines.coroutineContext

class MainRecyclerViewAdapter(val mainItemList:ArrayList<ProductItem>,val listener:Listener):RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder>() {

    class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val ivCardView:ImageView=itemView.findViewById(R.id.ivCardView)
        val titleTextView: TextView=itemView.findViewById(R.id.titleTextView)
        val categoryTextView:TextView=itemView.findViewById(R.id.categoryTextView)
        val oldPriceTextView:TextView=itemView.findViewById(R.id.oldPriceTextView)
        val newPriceTextView:TextView=itemView.findViewById(R.id.newPriceTextView)


        fun bindItems(productItem:ProductItem,listener:Listener){

            Glide.with(itemView.context)
                .load(productItem.imageUrl)
                .into(ivCardView)

            oldPriceTextView.paintFlags = oldPriceTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            titleTextView.text=productItem.productName

            oldPriceTextView.text=productItem.price
            newPriceTextView.text=productItem.priceDiscount
            if(productItem.category=="MALE"){

                categoryTextView.setBackgroundResource(R.drawable.main_cardview_men)
                categoryTextView.setTextColor(Color.parseColor("#1A2975FF"))
                categoryTextView.text=productItem.category

            }else if (productItem.category=="FEMALE"){

                categoryTextView.setBackgroundResource(R.drawable.main_cardview_women)
                categoryTextView.setTextColor(Color.parseColor("#1AFF66A0"))
                categoryTextView.text=productItem.category


            }

            itemView.setOnClickListener{
                listener.onItemClicked(productItem)
            }
        }
    }

    interface Listener {
        fun onItemClicked(productItem: ProductItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_cardview_general, parent,false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.bindItems(mainItemList[position], listener)

    }

    override fun getItemCount(): Int {
        return mainItemList.size
    }
    fun updateData(list: List<ProductItem>) {
        mainItemList.clear()
        mainItemList.addAll(list)
        notifyDataSetChanged()
    }

}