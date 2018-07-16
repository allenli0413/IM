package com.liyh.im.ui.activity

import com.liyh.im.R
import kotlinx.android.synthetic.main.header.*

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 16 日
 * @time  17 时 46 分
 * @descrip :
 */
class ChatActivity : BaseActivity(){
    override fun getLayoutId(): Int = R.layout.activity_chat

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.chat_title)
    }
}