package com.liyh.im.adapter

import com.hyphenate.EMContactListener

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 16 日
 * @time  18 时 12 分
 * @descrip :
 */
open class EmContactListenerAdapter: EMContactListener {
    override fun onContactInvited(p0: String?, p1: String?) {


    }

    override fun onContactDeleted(p0: String?) {
    }

    override fun onFriendRequestAccepted(p0: String?) {
    }

    override fun onContactAdded(p0: String?) {
    }

    override fun onFriendRequestDeclined(p0: String?) {
    }
}