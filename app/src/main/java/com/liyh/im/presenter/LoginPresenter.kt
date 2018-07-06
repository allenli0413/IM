package com.liyh.im.presenter

import com.liyh.im.contract.LoginContract
import com.liyh.im.extentions.isValidPassword
import com.liyh.im.extentions.isValidUserName

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 06 日
 * @time  17 时 34 分
 * @descrip :
 */
class LoginPresenter(val view: LoginContract.View) :LoginContract.Presenter{
    override fun login(userName: String, password: String) {
        if (userName.isValidUserName()) {
            if (password.isValidPassword()){
                view.onStartLogin()
            }else{
                view.onPasswordError()
            }
        } else{
            view.onUserNameError()
        }
    }
}