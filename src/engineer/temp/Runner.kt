package engineer.temp

import java.util.*


fun main() {

    val calendar = Calendar.getInstance()


    calendar.set(
        calendar[Calendar.YEAR], calendar[Calendar.MONTH], 1, 23, 59, 59
    )
    val today = calendar.timeInMillis
    println(today)

    calendar.set(
        calendar[Calendar.YEAR], calendar[Calendar.MONTH], 0, 23, 59, 59
    )
    val yesterday = calendar.timeInMillis
    println(yesterday)
    val diff = today - yesterday
    val day = diff / (60 * 60 * 24 * 1000)

    println("diff ==> $diff")
    println("day ==> $day")

    for (i in 0..10) {
        test(10)
    }
}

var wakeupNum = 0
private fun test(everyTime: Int) {

    wakeupNum = (wakeupNum + 1) % everyTime



    println(wakeupNum)
}