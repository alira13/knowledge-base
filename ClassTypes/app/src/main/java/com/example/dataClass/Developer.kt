package com.example.dataClass

// Класс разработчика
data class Developer(
    override val id: Int,
    override val name: String,
    override val role: String,
    val primaryLanguage: String
) : TeamMember(id, name, role) {

    // это от абстрактного класса + перегруженный метод
    override fun copy(id: Int, name: String, role: String): TeamMember {
        return copy(id, name, role, primaryLanguage)
    }

    // тут сгенерен copy, но его с теми же аргументами не переопределить, он final
    // да и не за чем, потому что он сгенерен со всеми возможными параметрами

    // есть hashCode(), его можно override
    // есть equals(), его можно override
    // есть toString(), его можно override

}



