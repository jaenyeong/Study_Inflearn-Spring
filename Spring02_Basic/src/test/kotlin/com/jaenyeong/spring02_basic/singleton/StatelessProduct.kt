package com.jaenyeong.spring02_basic.singleton

class StatelessProduct {
    fun price(name: String, price: Int): Int {
        println("name = $name price = $price")
        return price
    }
}
