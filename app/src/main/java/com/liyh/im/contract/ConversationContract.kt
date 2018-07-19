package com.liyh.im.contract

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 18 日
 * @time  18 时 19 分
 * @descrip :
 */
interface ConversationContract {
    interface Presenter: BasePresenter{
        fun loadConversation()
    }

    interface View{
        fun onStartLoadConversation()
        fun onLoadConversationSuccess()
        fun onLoadConversationFailed()
    }
}