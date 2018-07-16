package com.liyh.im.contract

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 16 日
 * @time  14 时 55 分
 * @descrip :
 */
interface ContactsContract {

    interface Presenter : BasePresenter {
        fun loadContacts()
    }

    interface View {
        fun onLoadContactsSuccess()
        fun onLoadContactsFailed()
    }
}