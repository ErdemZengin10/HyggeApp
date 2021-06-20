package com.erdemzengin.hyggeapp.models.main

import com.google.gson.annotations.SerializedName

data class CategoryItem(

    @SerializedName("id")
    val id:String?,
    @SerializedName("campaingName")
    val campaignName:String?,
    @SerializedName("imageUrl")
    val imageUrl:String?,
    @SerializedName("category")
    val category:String?


)
