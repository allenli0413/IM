package com.liyh.im.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.toast

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
    open fun init(){
    }

    /**
     * 获取布局Id
     */
    abstract fun getLayoutId(): Int

    fun showToast(strRes: Int) {
        context?.toast(getString(strRes))
    }
    fun showToast(text: String) {
        context?.toast(text)
    }
}