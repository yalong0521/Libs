package com.yalong.utils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object FileUtils {
    fun readFileToString(inputStream: InputStream): String? {
        var reader: BufferedReader? = null
        try {
            val sb = StringBuilder()
            reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            while (reader.readLine().apply { line = this } != null) {
                // windows系统换行为\r\n，Linux为\n
                sb.append(line).append("\r\n")
            }
            return if (sb.isNotEmpty()) {
                sb.delete(sb.length - 2, sb.length).toString()
            } else {
                sb.toString()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        } finally {
            CloseUtils.closeIO(reader)
            CloseUtils.closeIO(inputStream)
        }
    }
}