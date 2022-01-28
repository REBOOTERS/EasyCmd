package engineer.easy.internal.util

import javax.swing.JTextArea

internal object Printer {
    private lateinit var consoleArea: JTextArea
    fun init(consoleArea: JTextArea) {
        Printer.consoleArea = consoleArea
    }

    fun clear() {
        consoleArea.text = ""
    }

    fun println(str: String) {
        consoleArea.append(str)
        consoleArea.append("\r\n")
    }
}