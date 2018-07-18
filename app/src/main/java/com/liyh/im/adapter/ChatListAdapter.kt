package com.liyh.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hyphenate.chat.EMMessage
import com.hyphenate.util.DateUtils
import com.liyh.im.ui.widget.ChatListReceiveItemView
import com.liyh.im.ui.widget.ChatListSendItemView

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 18 日
 * @time  14 时 54 分
 * @descrip :
 */
class ChatListAdapter(val context: Context, val msgList: List<EMMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val ITEM_TYPE_SEND_MESSAGE = 0
        val ITEM_TYPE_RECEIVE_MESSAGE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (msgList[position].direct() == EMMessage.Direct.SEND) ITEM_TYPE_SEND_MESSAGE else ITEM_TYPE_RECEIVE_MESSAGE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = if (viewType == ITEM_TYPE_SEND_MESSAGE) ChatSendViewHolder(ChatListSendItemView(context))
    else ChatReceiveViewHolder(ChatListReceiveItemView(context))

    override fun getItemCount(): Int = msgList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val isShowTimestamp = isShowTimestamp(position)
        val itemModel = msgList[position]
        if (getItemViewType(position) == ITEM_TYPE_SEND_MESSAGE) {
            val itemView = holder.itemView as ChatListSendItemView
            itemView.bindData(itemModel, isShowTimestamp)
        } else {
            val itemView = holder.itemView as ChatListReceiveItemView
            itemView.bindData(itemModel, isShowTimestamp)
        }
    }

    private fun isShowTimestamp(position: Int): Boolean {
        var isShowTime = true
        if (position > 0) {
            isShowTime = !DateUtils.isCloseEnough(msgList[position].msgTime, msgList[position - 1].msgTime)
        }
        return isShowTime
    }

    class ChatSendViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    class ChatReceiveViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}