package com.example.dataClass

// Класс менеджера проекта
data class ProjectManager(
    override val id: Int,
    override val name: String,
    override val role: String,
    val projectsHandled: Int
) : TeamMember(id, name, role) {
    override fun copy(id: Int, name: String, role: String): ProjectManager {
        return copy(id=id, name=name, role=role, projectsHandled=projectsHandled)
    }
}