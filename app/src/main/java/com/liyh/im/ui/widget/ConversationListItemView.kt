package com.liyh.im.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import com.liyh.im.R
import kotlinx.android.synthetic.main.view_conversation_item.view.*
import java.util.*

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 18 日
 * @time  18 时 14 分
 * @descrip :
 */
class ConversationListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.view_conversation_item, this)
    }

    fun bindData(itemModel: EMConversation) {
        userName.text = itemModel.conversationId()
        if (itemModel.lastMessage.type == EMMessage.Type.TXT) {
            lastMessage.text = (itemModel.lastMessage.body as EMTextMessageBody).message
        } else {
            lastMessage.text = context.getString(R.string.no_text_message)
        }
        val time = DateUtils.getTimestampString(Date(itemModel.lastMessage.msgTime))
        timestamp.text = time
        if (itemModel.unreadMsgCount > 0) {
            unreadCount.visibility = View.VISIBLE
            unreadCount.text = itemModel.unreadMsgCount.toString()
        } else {
            unreadCount.visibility = View.GONE
        }
    }
}