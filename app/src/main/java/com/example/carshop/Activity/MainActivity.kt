package com.example.carshop.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.helper.widget.Grid
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carshop.Adapter.CategoryAdapter
import com.example.carshop.Adapter.CarsAdapter
import com.example.carshop.Domain.CategoryDomain
import com.example.carshop.Domain.CarDomain
import com.example.carshop.databinding.ActivityMainBinding
import com.google.firebase.database.*

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var carsAdapter: CarsAdapter

    private val categoryItems = ArrayList<CategoryDomain>()
    private val popularCarsItems = ArrayList<CarDomain>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()

        binding.recyclerViewCategory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter = CategoryAdapter(this, categoryItems)
        binding.recyclerViewCategory.adapter = categoryAdapter

        binding.recyclerViewPopular.layoutManager =
            GridLayoutManager(this, 2)
        carsAdapter = CarsAdapter(this, popularCarsItems)
        binding.recyclerViewPopular.adapter = carsAdapter

        binding.profilePic.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        initCategoryList()
        initPopularCars()
        bottomNavigation()


    }

    private fun bottomNavigation() {
        binding.profileBtn.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initCategoryList() {
        binding.progressBarCategory.visibility = View.VISIBLE

        database?.getReference("Category")?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                categoryItems.clear()

                if (snapshot.exists()) {
                    for (categorySnapshot in snapshot.children) {
                        val category = categorySnapshot.getValue(CategoryDomain::class.java)
                        category?.let { categoryItems.add(it) }
                    }
                    categoryAdapter.notifyDataSetChanged()
                }

                if (categoryItems.isEmpty()) {
                    Toast.makeText(
                        this@MainActivity,
                        "No categories available.",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.recyclerViewCategory.visibility = View.GONE
                } else {
                    binding.recyclerViewCategory.visibility = View.VISIBLE
                }

                binding.progressBarCategory.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                binding.progressBarCategory.visibility = View.GONE
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initPopularCars() {
        binding.progressBarPopular.visibility = View.VISIBLE

        database?.getReference("Cars")?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                popularCarsItems.clear()

                if (snapshot.exists()) {
                    for (carSnapshot in snapshot.children) {
                        val car = carSnapshot.getValue(CarDomain::class.java)
                        car?.let { popularCarsItems.add(it) }
                    }
                    carsAdapter.notifyDataSetChanged()
                }

                if (popularCarsItems.isEmpty()) {
                    Toast.makeText(
                        this@MainActivity,
                        "No popular cars available.",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.recyclerViewPopular.visibility = View.GONE
                } else {
                    binding.recyclerViewPopular.visibility = View.VISIBLE
                }

                binding.progressBarPopular.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                binding.progressBarPopular.visibility = View.GONE
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
