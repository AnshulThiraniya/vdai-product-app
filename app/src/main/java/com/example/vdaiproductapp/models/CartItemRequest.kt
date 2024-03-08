package com.example.vdaiproductapp.models


data class CartItemRequest(
                           val userId: Int,
                           val products: List<CartDatabaseModel>
)

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Double,
    val discountPercentage: Double,
    val discountedPrice: Double,
    val thumbnail: String
)


