package observer.mutableObservableExample

// Мониторинг данных с периодическим опросом
class UserMonitor() : Observer<String> {

    override fun onChanged(data: String) {
        println("UserMonitor: User data updated: $data")
    }
}