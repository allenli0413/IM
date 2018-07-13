package com.liyh.im.presenter

import android.text.TextUtils
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hyphenate.chat.EMClient
import com.liyh.im.contract.RegisterContract
import com.liyh.im.extentions.isValidPassword
import com.liyh.im.extentions.isValidUserName
import org.jetbrains.anko.doAsync

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 13 日
 * @time  15 时 43 分
 * @descrip :
 */
class RegisterPresenter(val view: RegisterContract.View) : RegisterContract.Presenter {
    override fun register(userName: String, password: String, confirmPassword: String) {
        if (userName.isValidUserName()) {
            if (password.isValidPassword()) {
                if (TextUtils.equals(password, confirmPassword)) {
                    view.onStartRegister()
                    registerBmob(userName, password)
                } else {
                    view.onConfirmPasswordError()
                }
            } else {
                view.onPasswordError()
            }
        } else {
            view.onUserNameError()
        }
    }

    /**
     * 注册bmob, adk已实现线程调度
     */
    private fun registerBmob(userName: String, password: String) {
        val bu = BmobUser()
        bu.username = userName
        bu.setPassword(password)
        bu.signUp(object : SaveListener<BmobUser>() {
            override fun done(p0: BmobUser?, p1: BmobException?) {
                if (p1 == null) {
                    //注册成功
                    //环信注册
                    registerEaseMob(userName, password)
                } else {
                    //注册失败
                    if (p1.errorCode == 202) {
                        view.onUserExist()
                    } else {
                        view.onRegisterFailed()
                    }
                }
            }

        })
    }

    /**
     * 注册环信，未实现线程调度，需要自己实现
     */
    private fun registerEaseMob(userName: String, password: String) {
        doAsync {
            try {
                EMClient.getInstance().createAccount(userName, password)
                uiThread { view.onRegisterSuccess() }
            } catch (e: Exception) {
                uiThread { view.onRegisterFailed() }
            }

        }

    }

}