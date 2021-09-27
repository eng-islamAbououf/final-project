package com.finalProject.myapplication.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Connection {
    var calls : Calls = Retrofit
    .Builder()
    .baseUrl("https://android-training.appssquare.com/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(Calls::class.java)

}