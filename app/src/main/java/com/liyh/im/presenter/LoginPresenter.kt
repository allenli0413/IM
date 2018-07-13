package com.liyh.im.presenter

import com.hyphenate.chat.EMClient
import com.liyh.im.adapter.EmCallbackAdapter
import com.liyh.im.contract.LoginContract
import com.liyh.im.extentions.isValidPassword
import com.liyh.im.extentions.isValidUserName

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 06 日
 * @time  17 时 34 分
 * @descrip :
 */
class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {
    override fun login(userName: String, password: String) {
        if (userName.isValidUserName()) {
            if (password.isValidPassword()) {
                view.onStartLogin()
                loginEaseMob(userName, password)
            } else {
                view.onPasswordError()
            }
        } else {
            view.onUserNameError()
        }
    }

    private fun loginEaseMob(userName: String, password: String) {
        EMClient.getInstance().login(userName, password, object : EmCallbackAdapter() {
            //环信回调在子线程
            override fun onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups()
                EMClient.getInstance().chatManager().loadAllConversations()
                //切换到主线程
                uiThread { view.onLoginSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { view.onLoginFailed() }
            }
        })
    }

}