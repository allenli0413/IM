package ui.activity

import android.os.Handler
import com.liyh.im.R
import com.liyh.im.contract.SplashContract
import com.liyh.im.presenter.SplashPresenter
import org.jetbrains.anko.startActivity

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 06 日
 * @time  15 时 15 分
 * @descrip :
 */
class SplashActivity: BaseActivity(),SplashContract.View {
    val handler by lazy { Handler() }
    val presenter by lazy { SplashPresenter(this) }
    val TIME_DELAY = 2000L

    override fun onNoLoggedIn() {
        handler.postDelayed({
            startActivity<LoginActivity>()
            finish()
        }, TIME_DELAY)
    }

    override fun onLoggedIn() {
        startActivity<MainActivity>()
        finish()
    }

    override fun getLayoutId(): Int = R.layout.activity_splash

    init {
        presenter.checkLoginStatus()
    }
}