package com.example.vdaiproductapp.models

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("products")
    var Products:ArrayList<ProductModel>
)
