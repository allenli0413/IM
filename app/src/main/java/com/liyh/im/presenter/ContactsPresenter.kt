package com.liyh.im.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.liyh.im.contract.ContactsContract
import com.liyh.im.datas.ContactsItemModel
import org.jetbrains.anko.doAsync

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 16 日
 * @time  15 时 06 分
 * @descrip :
 */
class ContactsPresenter(val view: ContactsContract.View) : ContactsContract.Presenter{
    val contactsList = mutableListOf<ContactsItemModel>()
    override fun loadContacts() {
        doAsync {
            try {
                contactsList.clear()
                val allContacts = EMClient.getInstance().contactManager().allContactsFromServer
                allContacts.sortBy { it[0] }
                allContacts.forEachIndexed { index, s ->
                    val isShowFirstLetter = index == 0 || s[index] != s[index - 1]
                    val contactsItemModel = ContactsItemModel(s, s[0].toString().toUpperCase(), isShowFirstLetter)
                    contactsList.add(contactsItemModel)
                }
                uiThread { view.onLoadContactsSuccess() }
            } catch (e: HyphenateException) {
                uiThread { view.onLoadContactsFailed() }
            }

        }
    }
}