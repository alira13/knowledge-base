package com.example.designpatterns.observer.mutableObservableExample

fun interface Observer<T> {
    fun onChanged(data: T)
}

interface Observable<T> {
    val currentValue: T
    val observers: List<Observer<T>>

    fun registerObserver(observer: Observer<T>)

    fun unregisterObserver(observer: Observer<T>)

    fun notifyObservers() {
        observers.forEach { it.onChanged(currentValue) }
    }
}

class MutableObservable<T>(initialValue: T) : Observable<T> {

    override var currentValue: T = initialValue
        set(value) {
            field = value
            notifyObservers()
        }

    override val observers: List<Observer<T>>
        get() = _observers

    private var _observers: MutableList<Observer<T>> = mutableListOf()


    override fun registerObserver(observer: Observer<T>) {
        _observers.add(observer)
        observer.onChanged(currentValue)
    }

    override fun unregisterObserver(observer: Observer<T>) {
        _observers.remove(observer)
    }
}