package com.example.vdaiproductapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.vdaiproductapp.models.AddToCartResponse
import com.example.vdaiproductapp.models.CartDatabaseModel
import com.example.vdaiproductapp.models.CartItemRequest
import com.example.vdaiproductapp.models.Product
import com.example.vdaiproductapp.models.ProductResponse
import com.example.vdaiproductapp.network.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepo {
    val liveResponse: MutableLiveData<ProductResponse> = MutableLiveData<ProductResponse>()
    val cartresponse:MutableLiveData<AddToCartResponse> = MutableLiveData<AddToCartResponse>()
    var resloding: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply {
        value=true
    }

    var Cartresult: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply {
        value = true
    }

    fun GetResponse(): MutableLiveData<ProductResponse> {

        val call= Retrofit.ApiCall.GetProducts()
        call.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if(response.isSuccessful){
                    resloding.value=false
                    liveResponse.value=response.body()
                }
            }
            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                resloding.value=false
            }

        })

        return liveResponse
    }

//    var myList : ArrayList<Product> = ArrayList<Product>()

    fun addToCart(saveList:ArrayList<CartDatabaseModel>): MutableLiveData<AddToCartResponse> {
//     myList.add(Product(1,"sss",1.00,1,1.00,3.00,2.9,"dgd"))
        val call = Retrofit.ApiCall.addToCart(CartItemRequest(1,saveList))
        call.enqueue(object : Callback<AddToCartResponse> {
            override fun onResponse(call: Call<AddToCartResponse>, response: Response<AddToCartResponse>
            ) {
                if (response.isSuccessful) {
                    Cartresult.value = false
                    cartresponse.value=response.body()
                }
            }

            override fun onFailure(call: Call<AddToCartResponse>, t: Throwable) {
                Cartresult.value=false
                t.message
            }
        })
        return cartresponse
    }
}