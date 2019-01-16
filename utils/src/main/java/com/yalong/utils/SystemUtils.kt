package com.yalong.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

object SystemUtils {
    /**
     * 隐藏软键盘
     * 可以和[.showKeyboard]搭配使用，进行键盘的显示隐藏控制。
     *
     * @param view 当前页面上任意一个可用的view
     */
    fun hideKeyboard(view: View?) {
        if (null == view) return
        val imm = view.context.applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        if (view is EditText) {
            view.clearFocus()
        }
    }

    /**
     * 针对给定的editText显示软键盘（editText会先获得焦点）.
     * 可以和[.hideKeyboard]搭配使用，进行键盘的显示隐藏控制。
     */
    fun showKeyboard(editText: EditText?, delay: Boolean) {
        if (null == editText || !editText.requestFocus()) return
        val imm =
            editText.context.applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (delay) {
            editText.postDelayed({ imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT) }, 200)
        } else {
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}