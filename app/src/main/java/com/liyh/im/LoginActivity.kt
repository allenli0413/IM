package com.liyh.im

import com.liyh.im.contract.LoginContract
import com.liyh.im.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 06 日
 * @time  15 时 17 分
 * @descrip :
 */
class LoginActivity : BaseActivity(), LoginContract.View {

    val presenter by lazy { LoginPresenter(this) }

    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onStartLogin() {
        showProgress(getString(R.string.logging))
    }

    override fun onLoginSuccess() {
        dismissProgress()
        startActivity<MainActivity>()
    }

    override fun onLoginFailed() {
        dismissProgress()
        toast(R.string.login_failed)
    }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun init() {
        super.init()
        login.setOnClickListener {
            startLogin()
        }

        password.setOnEditorActionListener { v, actionId, event ->
            startLogin()
            true
        }
    }

    /**
     * 开始登陆
     */
    private fun startLogin() {
        val userNameStr = userName.text.trim().toString()
        val passwordStr = password.text.trim().toString()
        presenter.login(userNameStr, passwordStr)
    }
}