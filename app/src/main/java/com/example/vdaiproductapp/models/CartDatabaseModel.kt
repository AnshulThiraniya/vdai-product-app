package com.example.vdaiproductapp.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class CartDatabaseModel(
    val productid:String,
    val title:String,
    val stock:String,
    val price:String,
    val discount:String,
    val brand:String,
    val category:String,
    val image: String,
    val discription: String,

) : Serializable
