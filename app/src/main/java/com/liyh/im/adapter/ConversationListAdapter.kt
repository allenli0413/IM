package com.liyh.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hyphenate.chat.EMConversation
import com.liyh.im.ui.activity.ChatActivity
import com.liyh.im.ui.widget.ConversationListItemView
import org.jetbrains.anko.startActivity

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 18 日
 * @time  18 时 11 分
 * @descrip :
 */
class ConversationListAdapter(val context: Context, val list: List<EMConversation>) : RecyclerView.Adapter<ConversationListAdapter.ConversationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder = ConversationViewHolder(ConversationListItemView(context))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        val itemModel = list[position]
        val itemView = holder.itemView as ConversationListItemView
        itemView.bindData(itemModel)
        itemView.setOnClickListener { context.startActivity<ChatActivity>("userName" to itemModel.conversationId()) }
    }


    class ConversationViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}