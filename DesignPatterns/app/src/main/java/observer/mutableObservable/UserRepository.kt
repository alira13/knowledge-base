package observer.mutableObservable

// Хранилище пользователей
class UserRepository{
    // Данные, с которыми мы работаем внутри репозитория
    private val _data = mutableListOf<String>()

    // 1 Данные, на изменение которых мы подписываемся
    val observableData =  MutableObservable<List<String>>(_data)

    // 1 Данные, на изменение которых мы подписываемся
    val observableDataSize =  MutableObservable<Int>(_data.size)

    // ---------------------методы репозитория ------------------------------------
    fun addUser(user: String) {
        _data.add(user)
        println("Add user $user")
        // уведомить подписчиков об изменении списка пользователей
        observableData.data = _data
        observableDataSize.data = _data.size
    }

    fun removeUser(user: String) {
        _data.remove(user)
        println("Remove user $user")
        // уведомить подписчиков об изменении списка пользователей
        observableData.data = _data
        observableDataSize.data = _data.size
    }
}