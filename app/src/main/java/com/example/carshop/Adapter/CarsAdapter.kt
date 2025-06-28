package com.example.carshop.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carshop.Activity.DetailActivity
import com.example.carshop.Domain.CarDomain
import com.example.carshop.R
import java.text.NumberFormat
import java.util.Locale

class CarsAdapter(
    private val context: Context,
    private val carList: List<CarDomain>
) : RecyclerView.Adapter<CarsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pic: ImageView = itemView.findViewById(R.id.pic)
        val titleTxt: TextView = itemView.findViewById(R.id.titleTxt)
        val priceTxt: TextView = itemView.findViewById(R.id.priceTxt)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_cars, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usFormat = NumberFormat.getNumberInstance(Locale.US)
        val car = carList[position]
        holder.titleTxt.text = car.title
        holder.priceTxt.text = "$${usFormat.format(car!!.price)}"

        Glide.with(context)
            .load(car.picUrl)
            .into(holder.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("car", car)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = carList.size
}
