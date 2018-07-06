package com.liyh.im.presenter

import com.hyphenate.chat.EMClient
import com.liyh.im.contract.SplashContract

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 06 日
 * @time  16 时 13 分
 * @descrip :
 */
class SplashPresenter(val view: SplashContract.View) : SplashContract.Presenter {
    override fun checkLoginStatus() {
        if (isLogin()) view.onLoggedIn() else view.onNoLoggedIn()
    }

    private fun isLogin(): Boolean = EMClient.getInstance().isConnected && EMClient.getInstance().isLoggedInBefore
}