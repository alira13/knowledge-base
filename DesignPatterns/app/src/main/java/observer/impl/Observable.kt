package observer.impl

// Где T-тип данных, на изменения которых мы подписываемся, например список строк
interface Observable<T> {
    // 1 Данные, на изменение которых мы подписываемся
    val data : T

    // 2 Подписчики
    val observers:List<Observer<T>>

    // 3 Метод подписки
    fun addOnDataChangeListener(observer: Observer<T>) {
        // не можем использовать, потому что нужен mutable list
        // поэтому только в реализации можем описать метод
        //listeners.add(listener)
    }

    // 4 Метод отписки
    fun deleteOnDataChangeListener(observer: Observer<T>) {
        // не можем использовать, потому что нужен mutable list
        // поэтому только в реализации можем описать метод
        //listeners.remove(listener)
    }

    // 5 Метод уведомления подписчиков
    fun notifyObservers() {
        observers.forEach { it.onDataChanged(data) }
    }
}