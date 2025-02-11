package com.example.dataClass

// Базовый абстрактный класс для сотрудников
abstract class TeamMember(
    open val id: Int,
    open val name: String,
    open val role: String
) {
    abstract fun copy(
        id: Int = this.id,
        name: String = this.name,
        role: String = this.role
    ): TeamMember
}