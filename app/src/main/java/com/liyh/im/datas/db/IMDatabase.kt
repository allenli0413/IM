package com.liyh.im.datas.db

import com.liyh.im.extentions.toVarargArray
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 18 日
 * @time  10 时 24 分
 * @descrip :
 */
class IMDatabase {
    companion object {
        val dbHelper = DatabaseHelper()
        val instance = IMDatabase()
    }

    fun saveContact(contactModel: ContactModel) {
        dbHelper.use {
            insert(ContactsTable.NAME, *contactModel.map.toVarargArray())
            //* 将数组展开变为可变参数
        }
    }

    fun getAllContacts(): List<ContactModel> = dbHelper?.use {
        select(ContactsTable.NAME).parseList(object : MapRowParser<ContactModel> {
            override fun parseRow(columns: Map<String, Any?>): ContactModel = ContactModel(columns.toMutableMap())

        })
    }

    fun deleteAllContacts() {
        dbHelper.use {
            delete(ContactsTable.NAME, null, null)
        }
    }
}