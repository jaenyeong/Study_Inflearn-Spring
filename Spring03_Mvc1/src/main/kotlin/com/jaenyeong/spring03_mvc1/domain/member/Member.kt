package com.jaenyeong.spring03_mvc1.domain.member

data class Member(
    val username: String,
    val age: Int,
) {
    var id: Long = 0L
        private set

    fun changeId(id: Long) {
        this.id = id
    }
}
