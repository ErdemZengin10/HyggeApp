package com.erdemzengin.hyggeapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erdemzengin.hyggeapp.R
import com.erdemzengin.hyggeapp.adapters.rvAdapter.MainRecyclerViewAdapter
import com.erdemzengin.hyggeapp.adapters.vpAdapter.MainViewPagerAdapter
import com.erdemzengin.hyggeapp.models.main.CategoryItem
import com.erdemzengin.hyggeapp.models.main.ProductItem
import com.erdemzengin.hyggeapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),MainRecyclerViewAdapter.Listener,MainViewPagerAdapter.Listener {
    private lateinit var productItems: List<ProductItem>
    private lateinit var categoryItems:List<CategoryItem>
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerAdapter :MainRecyclerViewAdapter
    private lateinit var viewPagerAdapter:MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerAdapter= MainRecyclerViewAdapter(arrayListOf(),this)
        viewPagerAdapter= MainViewPagerAdapter(arrayListOf(),this)



        //RecyclerView

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initRecycler()
        initViewPager()
        mainViewModel.getListOfProducts()
        observeData()

    }


    private fun initRecycler() {

        mainRecyclerView.layoutManager = GridLayoutManager(this@MainActivity,2)
        mainRecyclerView.adapter = recyclerAdapter
    }
    private fun initViewPager(){

        mainViewPager.offscreenPageLimit = 3
        mainViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        mainViewPager.adapter = viewPagerAdapter




    }
    private fun observeData(){

        mainViewModel.listOfItems.observe(this, Observer {

            if(it!=null){

                productItems=it.productList
                categoryItems=it.categoryList

                viewPagerAdapter.updateData(categoryItems)
                recyclerAdapter.updateData(productItems)
                println("null gelmedi")
            }
            else{
                println("null geldi")
            }
        })

        mainViewModel.errorLD.observe(this, Observer {

            println("ERROR: ${it.toString()}")

        })
    }

    override fun onItemClickedViewPager(categoryItem: CategoryItem) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", categoryItem.id)
        startActivity(intent)
    }
    override fun onItemClicked(productItem: ProductItem) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", productItem.id)
        startActivity(intent)
    }
}
