package com.yalong.utils

import java.io.*

object GenerateUtils {
    fun generateDimens(baseSW: SW) {
        val file = File("./app/src/main/res/values/dimens.xml")
        var reader: BufferedReader? = null
        val sw320 = StringBuilder()
        val sw480 = StringBuilder()
        val sw600 = StringBuilder()
        val sw720 = StringBuilder()
        val sw800 = StringBuilder()
        val sw960 = StringBuilder()
        val sw1280 = StringBuilder()
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
                    sw320.append(start).append(num * 320 / baseSW.size).append(end).append("\r\n")
                    sw480.append(start).append(num * 480 / baseSW.size).append(end).append("\r\n")
                    sw600.append(start).append(num * 600 / baseSW.size).append(end).append("\r\n")
                    sw720.append(start).append(num * 720 / baseSW.size).append(end).append("\r\n")
                    sw800.append(start).append(num * 800 / baseSW.size).append(end).append("\r\n")
                    sw960.append(start).append(num * 960 / baseSW.size).append(end).append("\r\n")
                    sw1280.append(start).append(num * 1280 / baseSW.size).append(end).append("\r\n")
                } else {
                    sw320.append(tempString).append("\r\n")
                    sw480.append(tempString).append("\r\n")
                    sw600.append(tempString).append("\r\n")
                    sw720.append(tempString).append("\r\n")
                    sw800.append(tempString).append("\r\n")
                    sw960.append(tempString).append("\r\n")
                    sw1280.append(tempString).append("\r\n")
                }
            }
            reader.close()
            println("<!--  sw320 -->")
            println(sw320)
            println("<!--  sw480 -->")
            println(sw480)
            println("<!--  sw600 -->")
            println(sw600)
            println("<!--  sw720 -->")
            println(sw720)
            println("<!--  sw800 -->")
            println(sw800)
            println("<!--  sw960 -->")
            println(sw960)
            println("<!--  sw1280 -->")
            println(sw1280)

            val sw320file = "./app/src/main/res/values-w320dp/dimens.xml"
            val sw480file = "./app/src/main/res/values-w480dp/dimens.xml"
            val sw600file = "./app/src/main/res/values-w600dp/dimens.xml"
            val sw720file = "./app/src/main/res/values-w720dp/dimens.xml"
            val sw800file = "./app/src/main/res/values-w800dp/dimens.xml"
            val sw960file = "./app/src/main/res/values-w960dp/dimens.xml"
            val sw1280file = "./app/src/main/res/values-w1280dp/dimens.xml"
            writeFile(sw320file, sw320.toString())
            writeFile(sw480file, sw480.toString())
            writeFile(sw600file, sw600.toString())
            writeFile(sw720file, sw720.toString())
            writeFile(sw800file, sw800.toString())
            writeFile(sw960file, sw960.toString())
            writeFile(sw1280file, sw1280.toString())
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

    enum class SW(val size: Int) {
        SW320(320),
        SW480(480),
        SW600(600),
        SW720(720),
        SW800(800),
        SW960(960),
        SW1280(1280);

        val value: Int
            get() = size
    }
}