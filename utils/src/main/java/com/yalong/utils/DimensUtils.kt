package com.yalong.utils

import android.content.res.Configuration
import java.io.*

object DimensUtils {
    /**
     * 生成适配的像素值
     */
    fun generateAdaptivePx(pxValue: Float, baseWidthInDp: WidthInDp, baseDPI: DPI): Float {
        val resources = AppUtils.getApplication().resources
        val dpValue = pxValue / (baseDPI.dip / DPI.MDPI.dip)
        val orientation = resources.configuration.orientation
        val widthPixels = when (orientation) {
            Configuration.ORIENTATION_PORTRAIT -> resources.displayMetrics.widthPixels
            Configuration.ORIENTATION_LANDSCAPE -> resources.displayMetrics.heightPixels
            else -> resources.displayMetrics.widthPixels
        }
        return dpValue / baseWidthInDp.widthInDp * widthPixels
    }

    /**
     * 生成dimens文件
     */
    fun generateDimens(baseWidthDp: WidthInDp) {
        val file = File("./app/src/main/res/values/dimens.xml")
        var reader: BufferedReader? = null
        val w320 = StringBuilder()
        val w360 = StringBuffer()
        val w480 = StringBuilder()
        val w600 = StringBuilder()
        val w720 = StringBuilder()
        val w800 = StringBuilder()
        val w960 = StringBuilder()
        val w1280 = StringBuilder()
        try {
            println("生成不同分辨率：")
            reader = BufferedReader(FileReader(file))
            var tempString: String?
            while (reader.readLine().apply { tempString = this } != null) {
                if (tempString!!.contains("</dimen>")) {
                    val start = tempString!!.substring(0, tempString!!.indexOf(">") + 1)
                    val end = tempString!!.substring(tempString!!.lastIndexOf("<") - 2)
                    val num = java.lang.Float.valueOf(
                        tempString!!.substring(
                            tempString!!.indexOf(">") + 1,
                            tempString!!.indexOf("</dimen>") - 2
                        )
                    )
                    w320.append(start).append(num * 320 / baseWidthDp.widthInDp).append(end).append("\r\n")
                    w360.append(start).append(num * 360 / baseWidthDp.widthInDp).append(end).append("\r\n")
                    w480.append(start).append(num * 480 / baseWidthDp.widthInDp).append(end).append("\r\n")
                    w600.append(start).append(num * 600 / baseWidthDp.widthInDp).append(end).append("\r\n")
                    w720.append(start).append(num * 720 / baseWidthDp.widthInDp).append(end).append("\r\n")
                    w800.append(start).append(num * 800 / baseWidthDp.widthInDp).append(end).append("\r\n")
                    w960.append(start).append(num * 960 / baseWidthDp.widthInDp).append(end).append("\r\n")
                    w1280.append(start).append(num * 1280 / baseWidthDp.widthInDp).append(end).append("\r\n")
                } else {
                    w320.append(tempString).append("\r\n")
                    w360.append(tempString).append("\r\n")
                    w480.append(tempString).append("\r\n")
                    w600.append(tempString).append("\r\n")
                    w720.append(tempString).append("\r\n")
                    w800.append(tempString).append("\r\n")
                    w960.append(tempString).append("\r\n")
                    w1280.append(tempString).append("\r\n")
                }
            }
            reader.close()
            println("<!-- w320 -->")
            println(w320)
            println("<!-- w360 -->")
            println(w360)
            println("<!-- w480 -->")
            println(w480)
            println("<!-- w600 -->")
            println(w600)
            println("<!-- w720 -->")
            println(w720)
            println("<!-- w800 -->")
            println(w800)
            println("<!-- w960 -->")
            println(w960)
            println("<!-- w1280 -->")
            println(w1280)

            val w320file = "./app/src/main/res/values-w320dp/dimens.xml"
            val w360file = "./app/src/main/res/values-w360dp/dimens.xml"
            val w480file = "./app/src/main/res/values-w480dp/dimens.xml"
            val w600file = "./app/src/main/res/values-w600dp/dimens.xml"
            val w720file = "./app/src/main/res/values-w720dp/dimens.xml"
            val w800file = "./app/src/main/res/values-w800dp/dimens.xml"
            val w960file = "./app/src/main/res/values-w960dp/dimens.xml"
            val w1280file = "./app/src/main/res/values-w1280dp/dimens.xml"
            writeFile(w320file, w320.toString())
            writeFile(w360file, w360.toString())
            writeFile(w480file, w480.toString())
            writeFile(w600file, w600.toString())
            writeFile(w720file, w720.toString())
            writeFile(w800file, w800.toString())
            writeFile(w960file, w960.toString())
            writeFile(w1280file, w1280.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e1: IOException) {
                    e1.printStackTrace()
                }
            }
        }
    }

    private fun writeFile(file: String, text: String) {
        var out: PrintWriter? = null
        try {
            val parentFile = File(file).parentFile
            var dirExist = true
            if (!parentFile.exists()) {
                dirExist = parentFile.mkdirs()
            }
            if (dirExist) {
                out = PrintWriter(BufferedWriter(FileWriter(file)))
                out.println(text)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            CloseUtils.closeIO(out)
        }
    }

    /**
     * 相对宽度DP值
     */
    enum class WidthInDp(val widthInDp: Int) {
        W320(320),
        W360(360),
        W480(480),
        W600(600),
        W720(720),
        W800(800),
        W960(960),
        W1280(1280);
    }

    /**
     * DPI
     */
    enum class DPI(val dip: Int) {
        LDPI(120),
        MDPI(160),
        HDPI(240),
        XHDPI(320),
        XXHDPI(480),
        XXXHDPI(640),
    }
}