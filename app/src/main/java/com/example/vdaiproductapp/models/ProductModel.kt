package com.example.vdaiproductapp.models

import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("id")
    var id:Int,

    @SerializedName("title")
    var title:String,

    @SerializedName("price")
    var price:Int,

    @SerializedName("rating")
    var rating:Double,

    @SerializedName("stock")
    var stock:Int,

    @SerializedName("discountPercentage")
    var discount:Double,

    @SerializedName("category")
    var category:String,

    @SerializedName("image")
    var imageUrl:List<String>,

    @SerializedName("description")
    var description:String,

    @SerializedName("brand")
    var brand:String,

    @SerializedName("thumbnail")
    var thumbnail:String
)
