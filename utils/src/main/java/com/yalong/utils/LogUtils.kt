package com.yalong.utils

import android.util.Log
import java.util.*

object LogUtils {
    private var mSwitch = true

    fun switch(status: Boolean) {
        mSwitch = status
    }

    private fun generateTag(): String {
        val caller = Throwable().stackTrace[2]
        var tag = "%s.%s(L:%d)"
        var callerClazzName = caller.className
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1)
        tag = String.format(Locale.CHINA, tag, callerClazzName, caller.methodName, caller.lineNumber)
        return tag
    }

    fun logD(content: String) {
        if (!mSwitch) return
        val tag = generateTag()

        Log.d(tag, content)
    }

    fun logD(content: String, tr: Throwable) {
        if (!mSwitch) return
        val tag = generateTag()

        Log.d(tag, content, tr)
    }

    fun logE(content: String) {
        if (!mSwitch) return
        val tag = generateTag()

        Log.e(tag, content)
    }

    fun logE(content: String, tr: Throwable) {
        if (!mSwitch) return
        val tag = generateTag()

        Log.e(tag, content, tr)
    }

    fun logI(content: String) {
        if (!mSwitch) return
        val tag = generateTag()

        Log.i(tag, content)
    }

    fun logI(content: String, tr: Throwable) {
        if (!mSwitch) return
        val tag = generateTag()

        Log.i(tag, content, tr)
    }

    fun logV(content: String) {
        if (!mSwitch) return
        val tag = generateTag()

        Log.v(tag, content)
    }

    fun logV(content: String, tr: Throwable) {
        if (!mSwitch) return
        val tag = generateTag()

        Log.v(tag, content, tr)
    }

    fun logW(content: String) {
        if (!mSwitch) return
        val tag = generateTag()

        Log.w(tag, content)
    }

    fun logW(content: String, tr: Throwable) {
        if (!mSwitch) return
        val tag = generateTag()

        Log.w(tag, content, tr)
    }

    fun logW(tr: Throwable) {
        if (!mSwitch) return
        val tag = generateTag()

        Log.w(tag, tr)
    }

    fun logWTF(content: String) {
        if (!mSwitch) return
        val tag = generateTag()

        Log.wtf(tag, content)
    }

    fun logWTF(content: String, tr: Throwable) {
        if (!mSwitch) return
        val tag = generateTag()

        Log.wtf(tag, content, tr)
    }

    fun logWTF(tr: Throwable) {
        if (!mSwitch) return
        val tag = generateTag()

        Log.wtf(tag, tr)
    }
}