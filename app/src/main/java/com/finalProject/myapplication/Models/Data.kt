package com.finalProject.myapplication.Models

data class Data(
        val id: Int,
        val image: String,
        val name: String,
        val products: ArrayList<Product>,
        val restaurant_lat: String,
        val restaurant_long: String
)
