package com.liyh.im.contract

import android.os.Handler
import android.os.Looper

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 06 日
 * @time  15 时 21 分
 * @descrip :
 */
interface BasePresenter {
    companion object {
        val handler by lazy { Handler(Looper.getMainLooper()) }
    }

    fun uiThread(f: () -> Unit) {
        handler.post { f() }
    }
}