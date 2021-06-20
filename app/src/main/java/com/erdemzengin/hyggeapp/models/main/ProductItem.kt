package com.erdemzengin.hyggeapp.models.main

import com.google.gson.annotations.SerializedName

data class ProductItem(
    @SerializedName("id")
    val id:String?,
    @SerializedName("brandName")
    val brandName :String?,
    @SerializedName("productName")
    val productName:String?,
    @SerializedName("category")
    val category :String?,
    @SerializedName("price")
    val price:String?,
    @SerializedName("priceDiscount")
    val priceDiscount:String?,
    @SerializedName("image")
    val imageUrl:String?







)
