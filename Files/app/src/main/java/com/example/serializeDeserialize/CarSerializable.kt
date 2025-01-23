package com.example.serializeDeserialize

class CarSerializable(
    val make: String,
    val model: String,
    val year: Int,
    val vin: String,
    val color: String,
)

fun serialize(car: CarSerializable): String {
    val result = "${car.make}%${car.model}%${car.year}%${car.vin}%${car.color}"
    return result
}

fun deserialize(carAsString: String): CarSerializable {
    val fields = carAsString.trim().split('%')
    return CarSerializable(fields[0], fields[1], fields[2].toInt(),fields[3],fields[4])
}