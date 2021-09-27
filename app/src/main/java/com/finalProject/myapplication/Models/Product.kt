package com.finalProject.myapplication.Models

data class Product(
        val id: Int,
        val image: String,
        val name: String,
        val price: Int,
        val quantity: Int,
        val restaurant_id: Int
)