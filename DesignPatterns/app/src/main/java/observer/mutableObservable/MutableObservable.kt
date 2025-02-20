package observer.mutableObservable

class MutableObservable<T>(private val initialValue: T) : Observable<T> {

    // Разница с обычной реализацией 1 Вот тут изменяемая дата,
    // которая как только в нее что-то положат, сразу всех оповещает.
    // Не нужно дополнительно вызывать методы оповещения
    override var data: T = initialValue
        set(value) {
            field = value
            notifyObservers()
        }

    // 2 Подписчики
    private var _observers: MutableList<Observer<T>> = mutableListOf()
    override val observers: List<Observer<T>>
        get() = _observers

    // 3 Метод подписки
    override fun addOnDataChangeListener(observer: Observer<T>) {
        // не можем использовать, потому что нужен mutable list
        // поэтому только в реализации можем описать метод
        _observers.add(observer)
        observer.onDataChanged(data)
    }

    // 4 Метод отписки
    override fun deleteOnDataChangeListener(observer: Observer<T>) {
        // не можем использовать, потому что нужен mutable list
        // поэтому только в реализации можем описать метод
        _observers.remove(observer)
    }

    // 5 Метод уведомления подписчиков
    override fun notifyObservers() {
        observers.forEach { it.onDataChanged(data) }
    }
}