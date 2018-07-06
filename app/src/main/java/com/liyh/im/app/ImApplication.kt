package com.liyh.im.app

import android.app.Application
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import com.liyh.im.BuildConfig

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 06 日
 * @time  17 时 21 分
 * @descrip :
 */
class ImApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        EMClient.getInstance().init(applicationContext, EMOptions())
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)
    }
}