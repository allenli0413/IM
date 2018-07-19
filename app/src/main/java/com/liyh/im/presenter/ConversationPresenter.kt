package com.liyh.im.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import com.liyh.im.contract.ConversationContract
import org.jetbrains.anko.doAsync

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 18 日
 * @time  18 时 22 分
 * @descrip :
 */
class ConversationPresenter(val view: ConversationContract.View) : ConversationContract.Presenter{
    val conversations = mutableListOf<EMConversation>()

    override fun loadConversation() {
        view.onStartLoadConversation()
        doAsync {

            try {
                conversations.clear()
                val allConversations = EMClient.getInstance().chatManager().allConversations
                conversations.addAll(allConversations.values)
                uiThread { view.onLoadConversationSuccess() }
            } catch (e: Exception) {
                uiThread { view.onLoadConversationFailed() }
            }

        }
    }

}