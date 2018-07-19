package com.liyh.im.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.liyh.im.adapter.EmCallbackAdapter
import com.liyh.im.contract.ChatContract
import org.jetbrains.anko.doAsync

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 18 日
 * @time  15 时 45 分
 * @descrip :
 */
class ChatPresenter(val view: ChatContract.View) : ChatContract.Presenter {

    companion object {
        val PAGESIZE = 10
    }


    override fun receiveMsg(userName: String, p0: MutableList<EMMessage>?) {
        p0?.let { msglist.addAll(it) }
        //获取会话
        val conversation = EMClient.getInstance().chatManager().getConversation(userName)
        //会话标记为已读
        conversation.markAllMessagesAsRead()
    }

    val msglist = mutableListOf<EMMessage>()

    override fun sendMsg(userName: String, message: String) {
        val emMsg = EMMessage.createTxtSendMessage(message, userName)
        emMsg.setMessageStatusCallback(object : EmCallbackAdapter() {
            override fun onSuccess() {
                uiThread {
                    view.onSendMsgSuccess()
                }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread {
                    view.onSendMsgFailed()
                }
            }
        })
        msglist.add(emMsg)
        view.onStartSendMsg()
        EMClient.getInstance().chatManager().sendMessage(emMsg)
    }

    override fun loadMessage(userName: String) {
        doAsync {
            val conversation = EMClient.getInstance().chatManager().getConversation(userName)
            conversation.markAllMessagesAsRead()
            msglist.addAll(conversation.allMessages)
            uiThread {
                view.onLoadMessage()
            }
        }
    }

    override fun loadMoreMessage(userName: String) {
        doAsync {
            val conversation = EMClient.getInstance().chatManager().getConversation(userName)
            val startMsgId = msglist[0].msgId
            val moreMsgFromDB = conversation.loadMoreMsgFromDB(startMsgId, PAGESIZE)
            if (moreMsgFromDB.isEmpty()) {
                uiThread { view.onLoadEmptyMsg() }
            } else {
                msglist.addAll(0, moreMsgFromDB)
                uiThread { view.onLoadMoreMsg(moreMsgFromDB.size) }
            }
        }
    }
}