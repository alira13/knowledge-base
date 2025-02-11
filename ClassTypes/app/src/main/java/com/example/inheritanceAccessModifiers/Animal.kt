package com.example.inheritanceAccessModifiers

open class Animal(val name:String) {
    public fun liveAnimalPublic() {
        println("$name live in public Animal area\n")
    }

    internal fun liveAnimalInternal() {
        println("$name live in internal Animal area\n")
    }

    protected fun liveAnimalProtected() {
        println("$name live in protected Animal area\n")
    }

    private fun livePrivate() {
        println("$name live in private Animal area\n")
    }
}