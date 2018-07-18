package com.liyh.im.adapter

import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMMessage

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 18 日
 * @time  16 时 25 分
 * @descrip :
 */
open class MessageListenerAdapter: EMMessageListener {
    override fun onMessageRecalled(p0: MutableList<EMMessage>?) {
    }

    override fun onMessageChanged(p0: EMMessage?, p1: Any?) {
    }

    override fun onCmdMessageReceived(p0: MutableList<EMMessage>?) {
    }

    override fun onMessageReceived(p0: MutableList<EMMessage>?) {
    }

    override fun onMessageDelivered(p0: MutableList<EMMessage>?) {
    }

    override fun onMessageRead(p0: MutableList<EMMessage>?) {
    }

}