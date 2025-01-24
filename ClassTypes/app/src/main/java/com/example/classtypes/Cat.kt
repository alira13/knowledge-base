package com.example.classtypes

class Cat(name: String) : CatFamily(name) {

    private var privateOwnerName: String = "Sasha"

    override fun mewCatFamily() {
        println("$name say mewwwww!")
    }

    fun tellPrivateInfo(cat: Cat) {
        privateOwnerName = "Ksusha"
        println("This is private info")
        println("My owner is $privateOwnerName and ${cat.name}'s owner is ${cat.privateOwnerName}\n")
    }

    fun doProtected(){
        liveAnimalProtected()
        liveCatFamilyProtected()
    }
}
