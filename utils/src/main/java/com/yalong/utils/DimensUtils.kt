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
        val sw320 = StringBuilder()
        val sw360 = StringBuffer()
        val sw480 = StringBuilder()
        val sw600 = StringBuilder()
        val sw720 = StringBuilder()
        val sw800 = StringBuilder()
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
                    sw320.append(start).append(num * 320 / baseWidthDp.widthInDp).append(end).append("\r\n")
                    sw360.append(start).append(num * 360 / baseWidthDp.widthInDp).append(end).append("\r\n")
                    sw480.append(start).append(num * 480 / baseWidthDp.widthInDp).append(end).append("\r\n")
                    sw600.append(start).append(num * 600 / baseWidthDp.widthInDp).append(end).append("\r\n")
                    sw720.append(start).append(num * 720 / baseWidthDp.widthInDp).append(end).append("\r\n")
                    sw800.append(start).append(num * 800 / baseWidthDp.widthInDp).append(end).append("\r\n")
                } else {
                    sw320.append(tempString).append("\r\n")
                    sw360.append(tempString).append("\r\n")
                    sw480.append(tempString).append("\r\n")
                    sw600.append(tempString).append("\r\n")
                    sw720.append(tempString).append("\r\n")
                    sw800.append(tempString).append("\r\n")
                }
            }
            reader.close()
            println("<!-- sw320 -->")
            println(sw320)
            println("<!-- sw360 -->")
            println(sw360)
            println("<!-- sw480 -->")
            println(sw480)
            println("<!-- sw600 -->")
            println(sw600)
            println("<!-- sw720 -->")
            println(sw720)
            println("<!-- sw800 -->")
            println(sw800)

            val sw320file = "./app/src/main/res/values-sw320dp/dimens.xml"
            val sw360file = "./app/src/main/res/values-sw360dp/dimens.xml"
            val sw480file = "./app/src/main/res/values-sw480dp/dimens.xml"
            val sw600file = "./app/src/main/res/values-sw600dp/dimens.xml"
            val sw720file = "./app/src/main/res/values-sw720dp/dimens.xml"
            val sw800file = "./app/src/main/res/values-sw800dp/dimens.xml"
            writeFile(sw320file, sw320.toString())
            writeFile(sw360file, sw360.toString())
            writeFile(sw480file, sw480.toString())
            writeFile(sw600file, sw600.toString())
            writeFile(sw720file, sw720.toString())
            writeFile(sw800file, sw800.toString())
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
     * 绝对宽度DP值
     */
    enum class WidthInDp(val widthInDp: Int) {
        SW320(320),
        SW360(360),
        SW480(480),
        SW600(600),
        SW720(720),
        SW800(800),
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