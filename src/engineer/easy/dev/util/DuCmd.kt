package engineer.easy.dev.util

import engineer.easy.internal.interfaces.CmdInterface
import engineer.easy.internal.util.CmdUtil
import engineer.easy.internal.log

class DuCmd : CmdInterface {

    override fun init() {
        CmdUtil.init()
    }

    override fun executeCmd(arg: String) {
        log("arg is $arg")
        executeSendBroadCastReceiver(arg)
    }

    private fun executeSendBroadCastReceiver(arg: String) {
        val prev = "adb shell am broadcast -a com.engineer.mini.app -e q $arg"
        log("$prev")
        CmdUtil.run(prev)
    }

    override fun open(arg: String) {
        super.open(arg)
        log("arg is $arg")
        val prev = "adb shell am start -n $arg"
        CmdUtil.run(prev)
    }

    override fun runAs(arg: String) {
        super.runAs(arg)
        log("arg is $arg")
        CmdUtil.run(arg)
    }
}