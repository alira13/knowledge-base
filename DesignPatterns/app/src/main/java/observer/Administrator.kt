package observer


class Administrator {
    private val repository = SafeDoubleCheckRepository.getInstance("1")

    fun work() {
        while (true) {
            println("Operations")
            print(Operation.entries.withIndex().forEach { (index, operation) ->
                println("${operation.title}: $index ")
            })
            println("\nEnter operation:")
            val operationCode = readln().toInt()
            val operation = Operation.entries[operationCode]
            when (operation) {
                Operation.ADD_USER -> addUser()
                Operation.DELETE_USER -> deleteUser()
                Operation.EXIT -> {
                    repository.saveChanges()
                    return
                }
            }
        }
    }

    private fun addUser() {
        println("Enter first name")
        val firstName = readln().trim()

        println("Enter last name")
        val lastName = readln().trim()

        println("Enter age")
        val age = readln().trim().toInt()

        repository.addPerson(firstName, lastName, age)
    }

    private fun deleteUser() {
        println("Enter id")
        val id = readln().trim().toInt()
        repository.deletePerson(id)
    }
}

fun main() {
    val persons = SafeDoubleCheckRepository.getInstance("1").persons
    persons.forEach { println(it) }
    println("--------------")
    Administrator().work()
}