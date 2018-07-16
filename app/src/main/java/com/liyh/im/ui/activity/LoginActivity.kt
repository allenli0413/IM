package com.liyh.im.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import com.liyh.im.R
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
        finish()
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
        newUser.setOnClickListener { startActivity<RegisterActivity>() }
    }

    /**
     * 开始登陆
     */
    private fun startLogin() {
        hideSoftKeyBoard()
        if (hasWriteExternalStoragePermission()) {
            val userNameStr = userName.text.trim().toString()
            val passwordStr = password.text.trim().toString()
            presenter.login(userNameStr, passwordStr)
        } else {
            requestWriteExternalStoragePermissions()
        }
    }

    /**
     * 申请内存卡写入权限
     */
    private fun requestWriteExternalStoragePermissions() {
        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this, permissions, 0)
    }

    /**
     * 检查是否有写内存卡的权限
     */
    private fun hasWriteExternalStoragePermission(): Boolean {
        val result = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startLogin()
        } else {
            toast(getString(R.string.permission_denied))
        }
    }
}