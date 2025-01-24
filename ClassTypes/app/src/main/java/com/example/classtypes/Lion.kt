package com.example.classtypes

class Lion(name: String) : CatFamily(name) {
    override fun mewCatFamily() {
        println("$name say arrrrrr!")
    }
}