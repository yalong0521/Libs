package com.yalong.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import java.lang.reflect.Method

object AppUtils {
    private var mApplication: Application? = null

    @SuppressLint("PrivateApi")
    fun getApplication(): Application {
        if (mApplication == null) {
            var method: Method
            try {
                method = Class.forName("android.app.AppGlobals").getDeclaredMethod("getInitialApplication")
                method.isAccessible = true
                mApplication = method.invoke(null) as Application
            } catch (e: Exception) {
                method = Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication")
                method.isAccessible = true
                mApplication = method.invoke(null) as Application
            }
        }
        return mApplication!!
    }

    /**
     * 获取app版本号
     */
    fun getAppVersionName(context: Context): String {
        val packageManager = context.packageManager
        val packageName = context.packageName
        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        return packageInfo.versionName
    }
}