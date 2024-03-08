package com.example.vdaiproductapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vdaiproductapp.databinding.CartListItemBinding
import com.example.vdaiproductapp.models.CartDatabaseModel
import com.squareup.picasso.Picasso

class CartAdapter(val mylist:List<CartDatabaseModel>):RecyclerView.Adapter<CartAdapter.myViewHolder>() {
    inner class myViewHolder(var binding: CartListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
     var view=CartListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mylist.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
          val Cartitem=mylist[position]
        holder.binding.crtTitle.text="Title--${Cartitem.title}"
        holder.binding.crtPrice.text="Price--${Cartitem.price}"
        holder.binding.crtLatitude.text="Lat--0.0"
        holder.binding.crtLongitude.text = "Long--0.0."
        Picasso.get().load(Cartitem.image).into(holder.binding.ivItem)


    }


}