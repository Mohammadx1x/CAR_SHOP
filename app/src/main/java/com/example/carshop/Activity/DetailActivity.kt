package com.example.carshop.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.carshop.Domain.CarDomain
import com.example.carshop.databinding.ActivityDetailBinding
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var car: CarDomain? = null
    val usFormat = NumberFormat.getNumberInstance(Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        car = intent.getSerializableExtra("car") as? CarDomain

        if (car != null) {
            binding.titleTxt.text = car!!.title
            binding.descriptionTxt.text = car!!.description
            binding.priceTxt.text = "$${usFormat.format(car!!.price)}"
            binding.ratingTxt.text = car!!.rating.toString()
            binding.totalCapacityTxt.text = car!!.TotalCapacity
            binding.topSpeedTxt.text = car!!.HighestSpeed
            binding.horsePowerTxt.text = car!!.EngineOutput

            Glide.with(this)
                .load(car!!.picUrl)
                .into(binding.pic)
        }

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.addToCartBtn.setOnClickListener {
            Toast.makeText(this, "Buy now clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
