package com.liyh.im.contract

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 17 日
 * @time  16 时 18 分
 * @descrip :
 */
interface AddFriendContract {

    interface Presenter: BasePresenter{
        fun startSearch(userNameStr: String)
    }

    interface View {
        fun onSearchSuccess()
        fun onSearchFailed()
    }
}