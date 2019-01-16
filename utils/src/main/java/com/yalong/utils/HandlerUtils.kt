package com.yalong.utils

import android.os.Handler
import android.os.Message

import java.lang.ref.WeakReference

object HandlerUtils {
    class HandlerHolder
        (listener: OnReceiveMessageListener) : Handler() {
        private var mListenerWeakReference: WeakReference<OnReceiveMessageListener>? = null

        init {
            mListenerWeakReference = WeakReference(listener)
        }

        override fun handleMessage(msg: Message) {
            if (mListenerWeakReference != null && mListenerWeakReference!!.get() != null) {
                mListenerWeakReference!!.get()?.handlerMessage(msg)
            }
        }
    }

    interface OnReceiveMessageListener {
        fun handlerMessage(msg: Message)
    }
}
