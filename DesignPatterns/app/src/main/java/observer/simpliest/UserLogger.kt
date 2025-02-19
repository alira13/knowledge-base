package observer.simpliest

// Хранилище пользователей

// Класс наблюдателя, который подписывается на изменения в UserRepository
class UserLogger(val name: String) {
    //  Реализовать метод onUsersChanged(), который выводит сообщение в консоль в формате:
    // [LOG] Пользователи обновлены: <список пользователей>
    fun onUsersChanged(users: List<String>) {
        println("$name Users were updated: $users")
    }
}

