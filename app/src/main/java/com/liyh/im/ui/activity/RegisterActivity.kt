package com.liyh.im.ui.activity

import com.liyh.im.R
import com.liyh.im.contract.RegisterContract
import com.liyh.im.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 13 日
 * @time  15 时 23 分
 * @descrip :
 */
class RegisterActivity : BaseActivity(), RegisterContract.View {

    val presenter by lazy { RegisterPresenter(this) }

    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onConfirmPasswordError() {
        confirmPassword.error = getString(R.string.confirm_password_error)
    }

    override fun onRegisterSuccess() {
        dismissProgress()
        toast(R.string.register_success)
        finish()
    }

    override fun onStartRegister() {
        showProgress(getString(R.string.registering))
    }

    override fun onRegisterFailed() {
        dismissProgress()
        toast(R.string.register_failed)
    }

    override fun onUserExist() {
        dismissProgress()
        toast(getString(R.string.user_already_exist))
    }

    override fun getLayoutId(): Int = R.layout.activity_register

    override fun init() {
        register.setOnClickListener { startRegister() }
        confirmPassword.setOnEditorActionListener { v, actionId, event ->
            startRegister()
            true
        }
    }

    /**
     * 开始注册
     */
    private fun startRegister() {
        hideSoftKeyBoard()
        val userNameStr = userName.text.trim().toString()
        val passwordStr = password.text.trim().toString()
        val confirmPasswordStr = confirmPassword.text.trim().toString()
        presenter.register(userNameStr, passwordStr, confirmPasswordStr)

    }
}