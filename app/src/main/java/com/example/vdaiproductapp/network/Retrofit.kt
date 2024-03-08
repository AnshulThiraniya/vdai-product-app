package com.example.vdaiproductapp.network

import com.example.vdaiproductapp.methods.ApiMethods
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit {
    private val RetrofitClient : Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
    }

    val ApiCall:ApiMethods by lazy {
          RetrofitClient.build().create(ApiMethods::class.java)
    }

}