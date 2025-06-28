package com.example.carshop.Domain

import java.io.Serializable

class CarDomain() : Serializable {

    var title: String? = null
    var picUrl: String? = null
    var price: Double? = null
    var EngineOutput: String? = null
    var HighestSpeed: String? = null
    var TotalCapacity: String? = null
    var description: String? = null
    var rating: Double? = null

    constructor(
        title: String,
        picUrl: String,
        price: Double,
        EngineOutput: String,
        HighestSpeed: String,
        TotalCapacity: String,
        description: String,
        rating: Double
    ) : this() {
        this.title = title
        this.picUrl = picUrl
        this.price = price
        this.EngineOutput = EngineOutput
        this.HighestSpeed = HighestSpeed
        this.TotalCapacity = TotalCapacity
        this.description = description
        this.rating = rating
    }
}
