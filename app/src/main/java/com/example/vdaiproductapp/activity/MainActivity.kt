package com.example.vdaiproductapp.activity

import android.Manifest
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vdaiproductapp.R
import com.example.vdaiproductapp.adapters.ProductAdapter
import com.example.vdaiproductapp.database.MyDataBase
import com.example.vdaiproductapp.databinding.ActivityMainBinding
import com.example.vdaiproductapp.methods.AddCartListner
import com.example.vdaiproductapp.methods.GetLocation
import com.example.vdaiproductapp.models.CartDatabaseModel
import com.example.vdaiproductapp.models.CartItemRequest
import com.example.vdaiproductapp.models.ProductModel
import com.example.vdaiproductapp.models.ProductResponse
import com.example.vdaiproductapp.viewmodel.ProductViewmodel

class MainActivity : AppCompatActivity(), AddCartListner {

    lateinit var binding: ActivityMainBinding
    lateinit var viewmodel: ProductViewmodel
    lateinit var myAdapter: ProductAdapter
    lateinit var mySqlDb: MyDataBase
    lateinit var myList: ArrayList<CartDatabaseModel>

    lateinit var getlocation:GetLocation
    var mylocatinlist:ArrayList<Double> = ArrayList()
    var productId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewmodel = ViewModelProvider(this)[ProductViewmodel::class.java]
        mySqlDb = MyDataBase(this)

        getlocation=GetLocation(this@MainActivity)

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("loading please wait...")
        progressDialog.setCancelable(false)


        viewmodel.ProductLoad.observe(this, Observer {
            if (it) {
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        })
        mylocatinlist= getlocation.getlocation()

        viewmodel.getProduct().observe(this, Observer {
            myAdapter = ProductAdapter(this, it, this)
            binding.rvProductList.layoutManager = LinearLayoutManager(this)
            binding.rvProductList.adapter = myAdapter
            myAdapter.notifyDataSetChanged()
        })
    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == getlocation.reqCode) {
            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mylocatinlist = getlocation.getlocation()

            } else {
                Toast.makeText(this, "Location Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun addToCart(productDetail: ProductModel) {

        productId = productDetail.id
        mySqlDb.createData(
            productId.toString(),
            productDetail.title,
            productDetail.stock.toString(),
            productDetail.price.toString(),
            productDetail.discount.toString(),
            productDetail.brand,
            productDetail.category,
            productDetail.thumbnail,
            productDetail.description,
            mylocatinlist.get(0).toString(),
            mylocatinlist.get(1).toString()
        )

        Toast.makeText(this, "item added", Toast.LENGTH_SHORT).show()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_cart -> {
                myList = mySqlDb.fetchData()
                val intent = Intent(this@MainActivity, CartActivity::class.java)
                intent.putExtra("mylist", myList)
                if (myList != null) {
                    viewmodel.addCart(myList).observe(this) {

                    }
                }
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

