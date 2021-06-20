package com.erdemzengin.hyggeapp.network.clients


import com.erdemzengin.hyggeapp.util.Constans
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MainApi {


    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {

        retrofit?.let {
            return it
        }?: run{
            retrofit =
                Retrofit.Builder()
                    .baseUrl(Constans.MAIN_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit!!
        }

    }
}