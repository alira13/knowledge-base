package observer.mutableObservableExample


class OrderMonitor : Observer<Int> {

    override fun onChanged(data: Int) {
        println("OrderMonitor: Order data updated: $data")
    }
}
