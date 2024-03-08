package com.example.vdaiproductapp.methods

import com.example.vdaiproductapp.models.ProductModel
import com.example.vdaiproductapp.models.ProductResponse

interface AddCartListner {
    fun addToCart(productDetail: ProductModel)
}