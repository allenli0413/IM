package com.liyh.im

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 05 日
 * @time  16 时 58 分
 * @descrip : activity基类
 */
abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()
    }

    /**
     * 初始化公共功能
     */
    open fun init() {
    }

    /**
     * 获取布局Id
     */
    abstract fun getLayoutId(): Int
}