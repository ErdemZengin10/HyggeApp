package com.erdemzengin.hyggeapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erdemzengin.hyggeapp.models.detail.DetailResponse
import com.erdemzengin.hyggeapp.network.`interface`.ApiInterface
import com.erdemzengin.hyggeapp.network.clients.DetailApi
import com.erdemzengin.hyggeapp.network.clients.MainApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel: ViewModel() {

    private lateinit var detailService: ApiInterface
    var listOfItems = MutableLiveData<DetailResponse?>()
    var errorLD = MutableLiveData<Throwable?>()


    @SuppressLint("CheckResult")
    fun getItem(id:String) {
        detailService = DetailApi.getClient().create(ApiInterface::class.java)
        detailService.getProductDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ rr ->
                    rr?.let {
                        listOfItems.value = rr
                        errorLD.value = null

                    } ?: run {
                        listOfItems.value = null
                        errorLD.value = null

                    }
                }, { error ->
                    errorLD.value = error
                    listOfItems.value = null
                })
    }




}