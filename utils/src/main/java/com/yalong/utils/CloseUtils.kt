package com.yalong.utils

import java.io.Closeable
import java.io.IOException

object CloseUtils {
    /**
     * 关闭IO
     *
     * @param closeables closeable
     */
    fun closeIO(vararg closeables: Closeable?) {
        for (closeable in closeables) {
            try {
                closeable?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 安静关闭IO
     *
     * @param closeables closeable
     */
    fun closeIOQuietly(vararg closeables: Closeable?) {
        for (closeable in closeables) {
            try {
                closeable?.close()
            } catch (ignored: IOException) {
            }
        }
    }
}
