package observer.impl


// Класс наблюдателя, который подписывается на изменения в UserRepository
class UserLogger(val name: String) : Observer<List<String>> {
    // Метод onUsersChanged(), который выводит сообщение в консоль в формате:
    // [LOG] Пользователи обновлены: <список пользователей>
    override fun onDataChanged(newData: List<String>) {
        println("$name Users were updated: $newData")
    }
}