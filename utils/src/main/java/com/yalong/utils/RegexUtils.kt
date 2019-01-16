package com.yalong.utils

object RegexUtils {
    private const val REGEX_PHONE_NUM = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}\$"

    fun isPhoneNum(num: String): Boolean {
        return num.matches(REGEX_PHONE_NUM.toRegex())
    }
}