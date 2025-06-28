package com.example.carshop.Domain

class CategoryDomain() {

    var title: String? = null
    var picUrl: String? = null
    var Id: Int? = null

    constructor(title: String, picUrl: String, Id: Int) : this() {
        this.title = title
        this.picUrl = picUrl
        this.Id = Id
    }
}
