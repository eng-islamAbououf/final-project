package com.finalProject.myapplication.Api

import com.finalProject.myapplication.Models.Data
import com.finalProject.myapplication.Models.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Calls {

    @GET("restaurants")
    fun getData(): Call<DataModel>
}