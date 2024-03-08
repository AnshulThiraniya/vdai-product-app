package com.example.vdaiproductapp.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vdaiproductapp.models.AddToCartResponse
import com.example.vdaiproductapp.models.CartDatabaseModel
import com.example.vdaiproductapp.models.CartItemRequest
import com.example.vdaiproductapp.models.ProductResponse
import com.example.vdaiproductapp.repository.ProductRepo

class ProductViewmodel: ViewModel() {

    var ProductRepository=ProductRepo()

    var ProductLoad : MutableLiveData<Boolean> = ProductRepository.resloding
     var cartLoad:MutableLiveData<Boolean> = ProductRepository.Cartresult

    fun getProduct(): LiveData<ProductResponse> {
        return ProductRepository.GetResponse()
    }

    fun addCart(cartItemRequest: ArrayList<CartDatabaseModel>):LiveData<AddToCartResponse> {
        return ProductRepository.addToCart(cartItemRequest)
    }


}