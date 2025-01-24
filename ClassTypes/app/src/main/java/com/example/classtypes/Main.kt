package com.example.classtypes


fun main() {
    val wolf = Animal("Wolf")
    // отсюда только доступ к public и protected
    wolf.liveAnimalPublic()
    wolf.liveAnimalInternal()

    // нельзя создать абстрактный класс
    //val catFamily = CatFamily()

    // доступ есть только к методам РОДИТЕЛЯ, к protected-бабушкам нет доступа
    val cat1 = Cat("Milka")

    // отсюда не получится вызвать protected методы, только внутри cat
    //cat1.protected()
    // можно прокинуть в public-метод private или protected-инфо
    cat1.liveCatFamilyPublicAnimalProtected()
    //Но Protected-cat метод уже не вызвать, доступен только в наследниках cat
    //cat1.liveCatFamilyProtectedAnimalProtected

    val cat2 = Cat("Slivka")
    cat1.tellPrivateInfo(cat2)

    val lion = Lion("Lion")

    val catFamilyAnimals = listOf(cat1, cat2,lion)
    for(animal in catFamilyAnimals) animal.mewCatFamily()
}