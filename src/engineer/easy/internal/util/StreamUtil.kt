package engineer.easy.internal.util

import kotlin.Throws
import java.io.IOException
import java.io.InputStreamReader
import java.io.BufferedReader
import java.io.InputStream
import java.lang.StringBuilder
import java.nio.charset.StandardCharsets

object StreamUtil {
    @Throws(IOException::class)
    fun StreamToStringWithReader(inputStream: InputStream?): String {
        val inputStreamReader = InputStreamReader(inputStream, StandardCharsets.UTF_8)
        val bufferedReader = BufferedReader(inputStreamReader)
        val sb = StringBuilder()
        var line: String? = ""
        while (bufferedReader.readLine().also { line = it } != null) {
            sb.append(line).append("\n")
        }
        return sb.toString()
    }

    @Throws(IOException::class)
    fun StreamToString(inputStream: InputStream): String {
        val sb = StringBuilder()
        val buffer = ByteArray(1024)
        var len: Int
        while (inputStream.read(buffer).also { len = it } != -1) {
            val str = String(buffer, 0, len)
            sb.append(str)
        }
        inputStream.close()
        return sb.toString()
    }
}