package com.liyh.im.adapter

import android.text.Editable
import android.text.TextWatcher

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 18 日
 * @time  11 时 45 分
 * @descrip :
 */
open class TextWatcherListenerAdapter: TextWatcher {
    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}