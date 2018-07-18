package com.liyh.im.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import com.liyh.im.R
import kotlinx.android.synthetic.main.view_receive_message_item.view.*
import java.util.*

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 18 日
 * @time  15 时 01 分
 * @descrip :
 */
class ChatListReceiveItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.view_receive_message_item, this)
    }

    fun bindData(message: EMMessage, showTimestamp: Boolean) {
        updateTimestamp(message, showTimestamp)
        updateMsg(message)
    }

    private fun updateMsg(message: EMMessage) {
        if (message.type == EMMessage.Type.TXT) {
            receiveMessage.text = (message.body as EMTextMessageBody).message
        } else {
            receiveMessage.text = context.getString(R.string.no_text_message)
        }
    }

    private fun updateTimestamp(message: EMMessage, showTimestamp: Boolean) {
        if (showTimestamp) {
            timestamp.visibility = View.VISIBLE
            timestamp.text = DateUtils.getTimestampString(Date(message.msgTime))
        } else {
            timestamp.visibility = View.GONE
        }
    }
}