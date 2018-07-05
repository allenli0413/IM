package com.liyh.im

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 05 日
 * @time  17 时 01 分
 * @descrip :fragment基类
 */
abstract class BaseFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
        = inflater.inflate(getLayoutId(), null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       init()
    }

    /**
     * 初始化方法
     */
    abstract fun init()

    /**
     * 获取布局Id
     */
    abstract fun getLayoutId(): Int
}