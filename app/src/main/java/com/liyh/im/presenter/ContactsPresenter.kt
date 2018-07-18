package com.liyh.im.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.liyh.im.contract.ContactsContract
import com.liyh.im.datas.ContactsItemModel
import com.liyh.im.datas.db.ContactModel
import com.liyh.im.datas.db.IMDatabase
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
                IMDatabase.instance.deleteAllContacts()
                val allContacts = EMClient.getInstance().contactManager().allContactsFromServer
                allContacts.sortBy { it[0] }
                allContacts.forEachIndexed { index, s ->
                    val isShowFirstLetter = index == 0 || s[0] != allContacts[index - 1][0]
                    val contactsItemModel = ContactsItemModel(s, s[0].toString().toUpperCase(), isShowFirstLetter)
                    contactsList.add(contactsItemModel)
                    val contactsModel = ContactModel(mutableMapOf("name" to s))
                    IMDatabase.instance.saveContact(contactsModel)
                }
                uiThread { view.onLoadContactsSuccess() }
            } catch (e: HyphenateException) {
                uiThread { view.onLoadContactsFailed() }
            }

        }
    }
}