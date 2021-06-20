package com.erdemzengin.hyggeapp.models.detail

import android.util.Size
import com.google.gson.annotations.SerializedName

data class DetailResponse(

    @SerializedName("id")
    val id:String?,
    @SerializedName("brandName")
    val brandName:String?,
    @SerializedName("productName")
    val productName:String?,
    @SerializedName("productDetailInfo")
    val productDetailInfo:String?,
    @SerializedName("categoty")
    val category:String?,
    @SerializedName("sizes")
    val sizes:List<String?>,
    @SerializedName("price")
    val price:String?,
    @SerializedName("priceDiscount")
    val priceDiscount:String?,
    @SerializedName("latitude")
    val latitude:String?,
    @SerializedName("longitude")
    val longitude:String?,
    @SerializedName("images")
    val images :List<String?>,
    val sizeModelList:ArrayList<SizeModel>

)
