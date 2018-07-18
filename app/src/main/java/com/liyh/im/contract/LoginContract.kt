package com.liyh.im.contract

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 06 日
 * @time  17 时 29 分
 * @descrip :
 */
interface LoginContract {

    interface Presenter: BasePresenter{
        fun login(userName: String, password: String)

    }

    interface View{
        fun onUserNameError()
        fun onPasswordError()
        fun onStartLogin()
        fun onLoginSuccess()
        fun onLoginFailed()
    }
}