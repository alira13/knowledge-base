package com.example.equalsMethod

class ManWithEquals(private val age: Int) {
    override fun equals(other: Any?): Boolean {
        if (other !is ManWithEquals) return false
        return this.age == other.age
    }
}