package com.yalong.utils

import android.support.annotation.StringRes
import android.view.Gravity
import android.widget.Toast

object ToastUtils {
    private var mToast: Toast? = null

    fun shortCenterToast(@StringRes hint: Int) {
        shortCenterToast(AppUtils.getApplication().getString(hint))
    }

    fun shortCenterToast(hint: String) {
        toast(hint, Toast.LENGTH_SHORT, Gravity.CENTER)
    }

    fun longCenterToast(@StringRes hint: Int) {
        longCenterToast(AppUtils.getApplication().getString(hint))
    }

    fun longCenterToast(hint: String) {
        toast(hint, Toast.LENGTH_LONG, Gravity.CENTER)
    }

    fun shortBottomToast(@StringRes hint: Int) {
        shortBottomToast(AppUtils.getApplication().getString(hint))
    }

    fun shortBottomToast(hint: String) {
        toast(hint, Toast.LENGTH_SHORT, Gravity.BOTTOM)
    }

    fun longBottomToast(@StringRes hint: Int) {
        longBottomToast(AppUtils.getApplication().getString(hint))
    }

    fun longBottomToast(hint: String) {
        toast(hint, Toast.LENGTH_LONG, Gravity.BOTTOM)
    }

    private fun toast(hint: String, duration: Int, gravity: Int) {
        if (mToast != null) {
            mToast?.cancel()
            mToast = null
        }
        mToast = Toast.makeText(AppUtils.getApplication(), hint, duration).also {
            it.duration = duration
            it.setText(hint)
            val offsetY = if (gravity == Gravity.BOTTOM) ConvertUtils.dp2px(50f).toInt() else 0
            it.setGravity(gravity, 0, offsetY)
            it.show()
        }
    }
}