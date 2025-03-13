package com.example.flowandchannels.stateAsFlow

fun main() {
    var versions = listOf("1.9.1.1", "1.20.1", "1.0.0", "1.1.14")

    var currentPosition = 0
    var max = 0
    var equalsVersionsList = mutableListOf<String>()
    var maxLength = 1

    while (true) {
        for (i in 0..<versions.count()) {
            val splitedVersion = versions[i].split('.')
            // один раз посчитаем максимальную длину
            if (i == 0) {
                val currentLength = splitedVersion.count()
                if (maxLength <= currentLength) maxLength = currentLength
            }

            var currentNum = 0
            if (currentPosition < maxLength)
                currentNum = splitedVersion[currentPosition].toInt()
            println(currentNum)


            if (currentNum == max) {
                equalsVersionsList.add(versions[i])
            }
            if (currentNum > max) {
                max = splitedVersion[currentPosition].toInt()
                equalsVersionsList = mutableListOf(versions[i])
            }
        }

        if (equalsVersionsList.count() == 1 || currentPosition == maxLength) {
            println("result  = ${equalsVersionsList[0]}")
            break
        }
        println(equalsVersionsList)
        currentPosition += 1
        versions = equalsVersionsList
    }
}