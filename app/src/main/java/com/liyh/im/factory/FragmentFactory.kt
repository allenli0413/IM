package com.liyh.im.factory

import android.support.v4.app.Fragment
import com.liyh.im.R
import com.liyh.im.ui.fragment.ContactsFragment
import com.liyh.im.ui.fragment.ConversationFragment
import com.liyh.im.ui.fragment.DynamicFragment

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 16 日
 * @time  09 时 52 分
 * @descrip :
 */
class FragmentFactory private constructor() {

    val conversations by lazy { ConversationFragment() }
    val contacts by lazy { ContactsFragment() }
    val dynamic by lazy { DynamicFragment() }

    companion object {
        val instance = FragmentFactory()
    }

    fun getFragment(tabId: Int): Fragment? {
        return when (tabId) {
            R.id.tab_conversation -> conversations
            R.id.tab_contacts -> contacts
            R.id.tab_dynamic -> dynamic
            else -> null
        }
    }
}