package com.liyh.im.datas.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.liyh.im.app.ImApplication
import org.jetbrains.anko.db.*

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 18 日
 * @time  09 时 42 分
 * @descrip :
 */
class DatabaseHelper(ctx: Context = ImApplication.instance)
    : ManagedSQLiteOpenHelper(ctx, NAME, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(ContactsTable.NAME, true,
                ContactsTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                ContactsTable.CONTACT to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(ContactsTable.NAME)
        onCreate(db)
    }

    companion object {
        val NAME = "im.db"
        val VERSION = 1
    }
}