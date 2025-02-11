package com.example.inheritanceAccessModifiers


abstract class CatFamily(name: String) : Animal(name) {

    fun liveCatFamilyPublicAnimalProtected(){
        println("This is CatFamily public function")
        liveAnimalProtected()
    }


    protected fun liveCatFamilyProtectedAnimalProtected(){
        println("This is CatFamily protected function")
        liveAnimalProtected()
    }

    public fun liveCatFamilyPublic() {
        println("$name live in public CatFamily area")
    }

    internal fun liveCatFamilyInternal() {
        println("$name live in internal CatFamily area")
    }

    protected fun liveCatFamilyProtected() {
        println("$name live in protected CatFamily area")
    }

    private fun liveCatFamilyPrivate() {
        println("$name live in private CatFamily area")
    }

    abstract fun mewCatFamily()
}