package com.yalong.utils

import android.support.annotation.ColorRes
import android.support.annotation.DimenRes

@Suppress("DEPRECATION")
object ConvertUtils {
    fun dpToPx(dpValue: Float): Int {
        return Math.round(dpValue * AppUtils.getApplication().resources.displayMetrics.density)
    }

    fun dpToExactPx(dpValue: Float): Float {
        return dpValue * AppUtils.getApplication().resources.displayMetrics.density
    }

    fun pxToDp(pxValue: Float): Int {
        return Math.round(pxValue / AppUtils.getApplication().resources.displayMetrics.density)
    }

    fun pxToExactDp(pxValue: Float): Float {
        return pxValue / AppUtils.getApplication().resources.displayMetrics.density
    }

    fun dimenIdToPx(@DimenRes dimenRes: Int): Int {
        return AppUtils.getApplication().resources.getDimensionPixelSize(dimenRes)
    }

    fun dimenIdToExactPx(@DimenRes dimenRes: Int): Float {
        return AppUtils.getApplication().resources.getDimension(dimenRes)
    }

    fun colorIdToValue(@ColorRes color: Int): Int {
        return AppUtils.getApplication().resources.getColor(color)
    }
}