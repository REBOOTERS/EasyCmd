package engineer.easy.internal

import engineer.easy.internal.util.Printer
import java.text.SimpleDateFormat
import java.util.*


// 支持中文
const val SUPPORT_CHINESE = "chcp 65001"


internal fun logHead(arg: String) {
    Printer.println("=================================>")
    Printer.println(getTime() + ": " + arg)
    Printer.println("<=================================")
}

internal fun log(arg: String) {
    Printer.println("${getTime()}: $arg")
}

internal fun getTime(): String {
    val time = System.currentTimeMillis()
    val dateTime = Date(time)
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:dd")
    return simpleDateFormat.format(dateTime)
}