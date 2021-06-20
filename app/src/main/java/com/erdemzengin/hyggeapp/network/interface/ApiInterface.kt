package com.erdemzengin.hyggeapp.network.`interface`

import com.erdemzengin.hyggeapp.models.detail.DetailResponse
import com.erdemzengin.hyggeapp.models.main.MainResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET(".")
    fun getListOfProducts(): Observable<MainResponse>

    @GET(".")
    fun getProductDetail(
        @Query("productId") productId: String
    ): Observable<DetailResponse>

}