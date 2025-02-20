package observer.mutableObservableExample

class PriceMonitor : Observer<Double> {

    override fun onChanged(data: Double) {
        println("PriceMonitor: Price was updated: $data")
    }
}