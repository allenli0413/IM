package com.liyh.im.app

import android.support.multidex.MultiDexApplication
import cn.bmob.v3.Bmob
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import com.liyh.im.BuildConfig

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 06 日
 * @time  17 时 21 分
 * @descrip :
 */
class ImApplication : MultiDexApplication() {
    companion object {
        lateinit var instance: ImApplication
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        EMClient.getInstance().init(applicationContext, EMOptions())
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)
        Bmob.initialize(applicationContext, "5f656277545c7f0153cc37478476771b")
    }
}