package com.example.vdaiproductapp.models

import com.google.gson.annotations.SerializedName

data class AddToCartResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)
