package com.jaenyeong.spring02_basic.singleton

class StatefulProduct(
    price: Int = 0
) {
    var price: Int
        private set

    init {
        this.price = price
    }

    fun price(name: String, price: Int) {
        println("name = $name price = $price")
        this.price = price
    }
}
