package com.liyh.im.contract

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 13 日
 * @time  15 时 28 分
 * @descrip :
 */
interface RegisterContract {

    interface Presenter : BasePresenter {
        fun register(userName: String, password: String, confirmPassword: String)
    }

    interface View {
        fun onUserNameError()
        fun onPasswordError()
        fun onConfirmPasswordError()
        fun onStartRegister()
        fun onRegisterSuccess()
        fun onRegisterFailed()
        fun onUserExist()
    }
}