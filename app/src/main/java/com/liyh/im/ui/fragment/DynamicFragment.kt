package com.liyh.im.ui.fragment

import com.hyphenate.chat.EMClient
import com.liyh.im.R
import com.liyh.im.adapter.EmCallbackAdapter
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import com.liyh.im.ui.activity.LoginActivity

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 16 日
 * @time  10 时 04 分
 * @descrip :
 */
class DynamicFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_dynamic

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.dynamic)
        val logoutStr = String.format(getString(R.string.logout), EMClient.getInstance().currentUser)
        logout.text = logoutStr

        logout.setOnClickListener { logout() }
    }

    /**
     * 退出
     */
    fun logout() {
        EMClient.getInstance().logout(true, object : EmCallbackAdapter() {
            override fun onSuccess() {
                super.onSuccess()
                context?.runOnUiThread {
                    toast(getString(R.string.logout_success))
                    startActivity<LoginActivity>()
                    activity?.finish()
                }
            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                context?.runOnUiThread {
                    toast(getString(R.string.logout_failed))
                }
            }
        })
    }
}
