package com.example.inheritanceAccessModifiers

class Lion(name: String) : CatFamily(name) {
    override fun mewCatFamily() {
        println("$name say arrrrrr!")
    }
}