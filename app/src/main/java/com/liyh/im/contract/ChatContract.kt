package com.liyh.im.contract

import com.hyphenate.chat.EMMessage

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 18 日
 * @time  15 时 42 分
 * @descrip :
 */
interface ChatContract {
    interface Presenter : BasePresenter {
        fun sendMsg(userName:String, message: String)
        fun receiveMsg(userName: String, p0: MutableList<EMMessage>?)
        fun loadMessage(userName: String)
        fun loadMoreMessage(userName: String)
    }

    interface View {
        fun onStartSendMsg()
        fun onSendMsgSuccess()
        fun onSendMsgFailed()
        fun onLoadMessage()
        fun onLoadMoreMsg(size: Int)
        fun onLoadEmptyMsg()
    }
}