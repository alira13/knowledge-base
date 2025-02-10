package getterSetter

class Worker {
    private var salary: Int = 100000

    //setter
    fun setSalary(salary: Int) {

        if (salary < 100000)
            println("Salary is too small")
        else
            this.salary = salary
    }

    //getter
    fun getSalary(): Int {
        return this.salary
    }

    var name: String = ""
        set(value) {
            if (value.isNotBlank())
                field = value
            else {
                println("Name must not be empty")
            }
        }

    var age: Int = 0
        set(value) {
            if (value < field) {
                println("New age must be more then previous $field")
            } else field = value
        }
        get() {
            println("Dont ask age")
            return field
        }
}