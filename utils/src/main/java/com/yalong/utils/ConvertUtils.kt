package com.yalong.utils

import android.support.annotation.ColorRes

@Suppress("DEPRECATION")
object ConvertUtils {
    fun dp2px(dpValue: Float): Float {
        return (dpValue * AppUtils.getApplication().resources.displayMetrics.density + 0.5).toFloat()
    }

    fun px2dp(pxValue: Float): Float {
        return (pxValue / AppUtils.getApplication().resources.displayMetrics.density + 0.5).toFloat()
    }

    fun colorId2Value(@ColorRes color: Int): Int {
        return AppUtils.getApplication().resources.getColor(color)
    }
}