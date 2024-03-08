package com.example.vdaiproductapp.methods

import com.example.vdaiproductapp.models.AddToCartResponse
import com.example.vdaiproductapp.models.CartItemRequest
import com.example.vdaiproductapp.models.ProductModel
import com.example.vdaiproductapp.models.ProductResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiMethods {
    @GET("/products")
    fun GetProducts():Call<ProductResponse>

    @POST("/cart/add")
    fun addToCart(@Body cartItem: CartItemRequest): Call<AddToCartResponse>


}