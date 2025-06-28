package com.example.carshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carshop.Domain.CategoryDomain
import com.example.carshop.databinding.ViewholderCategoryBinding

class CategoryAdapter(
    private val context: Context,
    private val categoryList: List<CategoryDomain>
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewholderCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoryList[position]
        holder.binding.titleTxt.text = category.title

        Glide.with(context)
            .load(category.picUrl)
            .into(holder.binding.pic)
    }

    override fun getItemCount(): Int = categoryList.size
}
