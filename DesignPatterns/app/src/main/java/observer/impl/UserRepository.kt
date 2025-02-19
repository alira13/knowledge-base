package observer.impl

// Хранилище пользователей
class UserRepository : Observable<List<String>> {

    // 1 Данные, на изменение которых мы подписываемся
    override val data: List<String>
        get() {
            return _data
        }
    private val _data = mutableListOf<String>()

    // 2 Подписчики
    private val _observers = mutableListOf<Observer<List<String>>>()
    override val observers: List<Observer<List<String>>>
        get() {
            return _observers
        }

    // 3 Метод подписки
    override fun addOnDataChangeListener(observer: Observer<List<String>>) {
        _observers.add(observer)
    }

    // 4 Метод отписки
    override fun deleteOnDataChangeListener(observer: Observer<List<String>>) {
        _observers.remove(observer)
    }

    // ---------------------методы репозитория ------------------------------------
    fun addUser(user: String) {
        _data.add(user)
        println("Add user $user")
        // уведомить подписчиков об изменении списка пользователей
        notifyObservers()
    }

    fun removeUser(user: String) {
        _data.remove(user)
        println("Remove user $user")
        // уведомить подписчиков об изменении списка пользователей
        notifyObservers()
    }
}