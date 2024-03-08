package com.example.vdaiproductapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.vdaiproductapp.models.CartDatabaseModel

class MyDataBase(context: Context) {

    private val mycontext: Context =context
    val DBNAME = "VdaiDb"
    val TABLE_NAME = "VdaiDataTable"
    val VERSION = 1

    val ID = "Id"
    val PRODUCTID="ProductId"
    val TITLE = "Title"
    val STOCK="Stock"
    val PRICE = "Price"
    val DISCOUNT = "Discount"
    val BRAND = "Brand"
    val CATEGORY = "Category"
    val IMAGE = "Image"
    val DISCRIPTION="Discription"
    val LAT="Latitude"
    val LONG="Longitude"


    val CREATE_TABLE =
        "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT,$PRODUCTID TEXT,$TITLE TEXT,$STOCK TEXT,$PRICE TEXT," +
                "$DISCOUNT TEXT,$BRAND TEXT,$CATEGORY TEXT,$IMAGE TEXT,$DISCRIPTION TEXT,$LAT TEXT,$LONG TEXT) "


    inner class MyOpenHelperClass(context: Context) : SQLiteOpenHelper(context, DBNAME, null, VERSION) {
        override fun onCreate(sqlLiteDb: SQLiteDatabase?) {
            sqlLiteDb?.execSQL(CREATE_TABLE)
        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(p0)
        }

        override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            super.onDowngrade(db, oldVersion, newVersion)
        }
    }

    val Sqlhelper=MyOpenHelperClass(context)
    val SqlDb=Sqlhelper.writableDatabase

    fun  createData(productid:String ,title:String,stock:String,price:String,discount:String,brand:String,category:String,
                    image:String,discription:String,lat:String,long:String){

        val values= ContentValues()

        values.put(PRODUCTID,productid)
        values.put(TITLE,title)
        values.put(STOCK,stock)
        values.put(PRICE,price)
        values.put(DISCOUNT,discount)
        values.put(BRAND,brand)
        values.put(CATEGORY,category)
        values.put(IMAGE,image)
        values.put(DISCRIPTION,discription)
        values.put(LAT,lat)
        values.put(LONG,long)

        SqlDb.insertWithOnConflict(TABLE_NAME,null,values,SQLiteDatabase.CONFLICT_REPLACE)

    }

    fun fetchData(): ArrayList<CartDatabaseModel> {
        val dataList = ArrayList<CartDatabaseModel>()
        val myCursor = SqlDb.rawQuery("SELECT * FROM $TABLE_NAME", null,null)

        if (myCursor.moveToFirst()) {
            do {
                val productid=myCursor.getString(1)
                val title = myCursor.getString(2)
                val stock=myCursor.getString(3)
                val price = myCursor.getString(4)
                val discount = myCursor.getString(5)
                val brand = myCursor.getString(6)
                val category = myCursor.getString(7)
                val image = myCursor.getString(8)
                val discription = myCursor.getString(9)


                val data = CartDatabaseModel(productid, title, stock, price, discount, brand, category, image, discription)
                dataList.add(data)
            } while (myCursor.moveToNext())
        }

        return dataList
    }

    fun deleteSingleData(rowId:Int){
            val deletedRow=SqlDb.delete(TABLE_NAME,"$PRODUCTID=$rowId",null)
        if(deletedRow>0){
            Toast.makeText(mycontext,"$deletedRow row delete", Toast.LENGTH_LONG).show()
        }
    }

}
