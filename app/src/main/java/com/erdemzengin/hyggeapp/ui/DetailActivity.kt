package com.erdemzengin.hyggeapp.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erdemzengin.hyggeapp.R
import com.erdemzengin.hyggeapp.adapters.rvAdapter.MainRecyclerViewAdapter
import com.erdemzengin.hyggeapp.adapters.rvAdapter.SizeRecyclerViewAdapter
import com.erdemzengin.hyggeapp.adapters.vpAdapter.DetailViewPagerAdapter
import com.erdemzengin.hyggeapp.models.detail.DetailResponse
import com.erdemzengin.hyggeapp.models.main.ProductItem
import com.erdemzengin.hyggeapp.util.CustomDialog
import com.erdemzengin.hyggeapp.util.OnClickInterface
import com.erdemzengin.hyggeapp.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.size_cardview.*
import java.text.ParsePosition

class DetailActivity : AppCompatActivity(),SizeRecyclerViewAdapter.Listener{
        private lateinit var currentProductItem: DetailResponse
        private lateinit var detailViewModel:DetailViewModel
        private lateinit var itemLat:String
        private lateinit var itemLng:String
        private lateinit var sizeList:List<String>
        private lateinit var sizeRecyclerAdapter:SizeRecyclerViewAdapter
        private lateinit var imageList: List<String>
        private lateinit var detailViewPagerAdapter:DetailViewPagerAdapter
        private var numberOfItems=1
        private var flag:Boolean=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)




        sizeRecyclerAdapter= SizeRecyclerViewAdapter(arrayListOf(),this)
        detailViewPagerAdapter= DetailViewPagerAdapter(arrayListOf())



        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        initSizeRecycler()
        initDetailViewPager()
        val id = intent.getStringExtra("id")
        if (id != null) {
            detailViewModel.getItem(id)
        }
        observeLiveData()

        goToMapsBtn.setOnClickListener {

            val intent=Intent(this@DetailActivity,MapsActivity2::class.java)
            intent.putExtra("lat",itemLat)
            intent.putExtra("lng",itemLng)

            startActivity(intent)

        }
        right_button_detail.setOnClickListener {
            if (numberOfItems in 0..9){

                numberOfItems++
                number_items_text.text=numberOfItems.toString()

            }



        }
        left_button_detail.setOnClickListener {
            if (numberOfItems in 1..10){
                numberOfItems--
                number_items_text.text=numberOfItems.toString()

            }


        }
        add_cart_btn.setOnClickListener {

            showCustomDialog()
        }
        goBackButton.setOnClickListener {
            super.onBackPressed()
        }



    }
    private fun observeLiveData(){
        detailViewModel.listOfItems.observe(this, Observer {


            if (it!=null){

                val myImage=it.images[0]
                currentProductItem=it

                detailOldPriceText.paintFlags = detailOldPriceText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                detailBrandNameText.text=it.brandName
                detailOldPriceText.text=it.price
                detailNewPriceText.text=it.priceDiscount
                longDetailText.text=it.productDetailInfo
                itemLat=it.latitude!!
                itemLng=it.longitude!!

                sizeList= it.sizes as List<String>
                imageList=it.images as List<String>

                detailViewPagerAdapter.updateData(imageList)
                sizeRecyclerAdapter.updateData(sizeList)
            }
        })
    }

    override fun onItemClicked(view: View) {
        Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show()
        flag=true
        view.setBackgroundDrawable(ContextCompat.getDrawable(this@DetailActivity,R.drawable.size_item_background_clicked))

            add_cart_btn.setBackgroundResource(R.drawable.add_cart_btn_clicked)
            add_cart_btn.isClickable=true



    }
    private fun initSizeRecycler(){
        sizeRecyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        sizeRecyclerView.adapter=sizeRecyclerAdapter

    }
    private fun initDetailViewPager(){

        detailViewPager.offscreenPageLimit = 3
        detailViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        detailViewPager.adapter = detailViewPagerAdapter

    }
    private fun showCustomDialog() {
        CustomDialog(object : OnClickInterface {
            override fun onClickConfirm(dialog: Dialog) {
                dialog.dismiss()

            }


        }).show(supportFragmentManager, "MyCustomDialog")
    }


}


