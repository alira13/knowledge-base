package getterSetter

class Rectangle(var width: Int, var height: Int) {

    val area: Int
        get() {
            return width * height
        }
}