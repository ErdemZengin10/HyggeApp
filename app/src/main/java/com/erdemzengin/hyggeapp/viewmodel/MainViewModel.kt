package com.erdemzengin.hyggeapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erdemzengin.hyggeapp.models.main.MainResponse
import com.erdemzengin.hyggeapp.network.`interface`.ApiInterface
import com.erdemzengin.hyggeapp.network.clients.MainApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel:ViewModel() {

    private lateinit var mainService:ApiInterface

    var listOfItems = MutableLiveData<MainResponse?>()
    var errorLD = MutableLiveData<Throwable?>()

    @SuppressLint("CheckResult")
    fun getListOfProducts() {
        mainService = MainApi.getClient().create(ApiInterface::class.java)
        mainService.getListOfProducts()
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