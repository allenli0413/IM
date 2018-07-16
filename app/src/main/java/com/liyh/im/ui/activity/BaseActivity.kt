package com.liyh.im.ui.activity

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 05 日
 * @time  16 时 58 分
 * @descrip : activity基类
 */
abstract class BaseActivity : AppCompatActivity() {

    val progressDialog by lazy { ProgressDialog(this) }
    val inputMethodManager by lazy { getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }

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

    fun showProgress(msg: String) {
        progressDialog.setMessage(msg)
        progressDialog.show()
    }

    fun dismissProgress() {
        progressDialog.dismiss()
    }

    fun hideSoftKeyBoard() {
        if (inputMethodManager.isActive)
            inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }
}