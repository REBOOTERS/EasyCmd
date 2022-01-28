package engineer.easy.internal.util

import engineer.easy.internal.SUPPORT_CHINESE
import engineer.easy.internal.log
import engineer.easy.internal.logHead
import java.util.*

object CmdUtil {
    private val os = System.getProperty("os.name").lowercase(Locale.getDefault())

    fun init() {
        logHead("Welcome To $os")
        run(SUPPORT_CHINESE,true)
    }

    fun run(arg: String, silent: Boolean = false) {
        if (os.contains("mac")) {
            runOnMac(arg)
        } else if (os.contains("windows")) {
            runOnWindows(arg, silent)
        }
    }

    private fun runOnWindows(arg: String, silent: Boolean = false) {
        val runtime = Runtime.getRuntime()
        val realCmd = "cmd /c $arg"
        val p = runtime.exec(realCmd)
        val result = StreamUtil.StreamToStringWithReader(p.inputStream)
        if (silent.not()) {
            log(result)
        }
    }

    private fun runOnMac(arg: String) {
        val runtime = Runtime.getRuntime()
        val p = runtime.exec(arg)
        val result = StreamUtil.StreamToStringWithReader(p.inputStream)
        log(result)
    }


}