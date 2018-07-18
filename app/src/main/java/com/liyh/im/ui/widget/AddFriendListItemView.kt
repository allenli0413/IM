package com.liyh.im.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMClient
import com.liyh.im.R
import com.liyh.im.adapter.EmCallbackAdapter
import com.liyh.im.datas.AddFriendItemModel
import kotlinx.android.synthetic.main.view_add_friend_item.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 17 日
 * @time  16 时 26 分
 * @descrip :
 */
class AddFriendListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.view_add_friend_item, this)
    }

    fun setData(itemModel: AddFriendItemModel) {
        userName.text = itemModel.userName
        timestamp.text = itemModel.timestamp
        add.isEnabled = !itemModel.isAdd
        add.text = if (itemModel.isAdd) context.getString(R.string.already_added) else context.getString(R.string.add)
        add.setOnClickListener {
            addFriend(itemModel.userName)
        }
    }

    private fun addFriend(userName: String) {
        EMClient.getInstance().contactManager().aysncAddContact(userName, null, object : EmCallbackAdapter() {
            override fun onSuccess() {
                context.runOnUiThread { toast(R.string.send_message_success) }
            }

            override fun onError(p0: Int, p1: String?) {
                context.runOnUiThread { toast(R.string.send_message_failed) }

            }
        })
    }
}