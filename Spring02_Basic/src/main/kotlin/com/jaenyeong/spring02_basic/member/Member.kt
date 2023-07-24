package com.jaenyeong.spring02_basic.member

class Member(
    var id: Long = 0L,
    var name: String,
    var grade: Grade
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member

        if (id != other.id) return false
        if (name != other.name) return false
        return grade == other.grade
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + grade.hashCode()
        return result
    }

    override fun toString(): String {
        return "Member(id=$id, name='$name', grade=$grade)"
    }
}
