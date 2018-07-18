package com.liyh.im.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hyphenate.chat.EMClient
import com.liyh.im.contract.AddFriendContract
import com.liyh.im.datas.AddFriendItemModel
import com.liyh.im.datas.db.IMDatabase
import org.jetbrains.anko.doAsync

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 17 日
 * @time  16 时 20 分
 * @descrip :
 */
class AddFriendPresenter(val view: AddFriendContract.View) :AddFriendContract.Presenter{
val addFriendList = mutableListOf<AddFriendItemModel>()

    override fun startSearch(userNameStr: String) {
        val query = BmobQuery<BmobUser>()
        query.addWhereContains("username", userNameStr)
                .addWhereNotEqualTo("username", EMClient.getInstance().currentUser)
        query.findObjects(object : FindListener<BmobUser>() {
            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {
                if (p1 == null) {
                    val allContacts = IMDatabase.instance.getAllContacts()
                    doAsync {
                        addFriendList.clear()
                        p0?.forEach {
                            var isAdd = false
                            for (contact in allContacts) {
                                if (contact.name == it.username)
                                    isAdd = true
                            }
                            val addFriendItem = AddFriendItemModel(it.username, it.createdAt, isAdd)
                            addFriendList.add(addFriendItem)
                        }
                        uiThread { view.onSearchSuccess() }
                    }

                } else{
                    view.onSearchFailed()
                }
            }

        })
    }
}