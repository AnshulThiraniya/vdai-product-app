package com.example.vdaiproductapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vdaiproductapp.R
import com.example.vdaiproductapp.adapters.CartAdapter
import com.example.vdaiproductapp.databinding.ActivityCartBinding
import com.example.vdaiproductapp.databinding.CartListItemBinding
import com.example.vdaiproductapp.models.CartDatabaseModel

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    lateinit var adapter: CartAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=DataBindingUtil.setContentView(this,R.layout.activity_cart)
       var myList = intent.getSerializableExtra("mylist") as ArrayList<CartDatabaseModel>
        binding.rvCartList.layoutManager = LinearLayoutManager(this)
        adapter = CartAdapter(myList)
        binding.rvCartList.adapter= adapter
        setTitle("MyCart")

    }
}