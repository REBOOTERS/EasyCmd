package engineer.easy.internal

import javax.swing.JFrame

abstract class Page : JFrame() {
    abstract fun open()
}