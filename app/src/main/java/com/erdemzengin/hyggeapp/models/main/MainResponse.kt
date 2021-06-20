package com.erdemzengin.hyggeapp.models.main

import com.google.gson.annotations.SerializedName

data class MainResponse(

    @SerializedName("mainTitle")
    val mainTitle:String?,
    @SerializedName("subTitle")
    val subTitle:String?,
    @SerializedName("productList")
    val productList:List<ProductItem>,
    @SerializedName("categoryList")
    val categoryList:List<CategoryItem>



)
