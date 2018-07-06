package com.liyh.im.contract

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 06 日
 * @time  15 时 21 分
 * @descrip : 协议类
 */
interface SplashContract {

    interface Presenter: BasePresenter {
        /**
         * 检查登陆状态
         */
        fun checkLoginStatus()
    }

    interface View{
        /**
         * 没有登陆
         */
        fun onNoLoggedIn()

        /**
         * 已经登陆
         */
        fun onLoggedIn()
    }
}