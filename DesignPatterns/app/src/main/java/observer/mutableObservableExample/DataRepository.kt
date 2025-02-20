package observer.mutableObservableExample

import kotlin.math.round

// Репозиторий данных
object DataRepository {
    private var _userDataValue: String = "User_Initial"
    private var _orderDataValue: Int = 100
    private var _priceDataValue: Double = 99.99

    private val _orderData = MutableObservable<Int>(100)
    private val _priceData = MutableObservable<Double>(99.99)
    private val _userData = MutableObservable<String>("User_Initial")

    val orderData: Observable<Int>
        get() = _orderData
    val priceData: Observable<Double>
        get() = _priceData
    val userData: Observable<String>
        get() = _userData

    // Метод обновления данных
    fun updateData(newUser: String? = null, newOrder: Int? = null, newPrice: Double? = null) {
        newUser?.let {
            _userDataValue = it
            _userData.currentValue = _userDataValue
        }

        newOrder?.let {
            _orderDataValue = it
            _orderData.currentValue = _orderDataValue
        }
        newPrice?.let {
            _priceDataValue = round(it * 100) / 100
            _priceData.currentValue = _priceDataValue
        }
    }
}