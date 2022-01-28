package engineer.easy.internal.interfaces

interface CmdInterface {
    fun init()
    fun executeCmd(arg: String)
    fun open(arg: String) {}
    fun runAs(arg: String) {}
}