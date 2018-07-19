package com.liyh.im.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.liyh.im.R
import com.liyh.im.adapter.ConversationListAdapter
import com.liyh.im.adapter.MessageListenerAdapter
import com.liyh.im.contract.ConversationContract
import com.liyh.im.presenter.ConversationPresenter
import com.liyh.im.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 16 日
 * @time  10 时 04 分
 * @descrip :
 */
class ConversationFragment : BaseFragment(), ConversationContract.View {

    val presenter by lazy { ConversationPresenter(this) }
    lateinit var attAct: MainActivity
    private val messageListener = object : MessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            presenter.loadConversation()
        }
    }

    override fun init() {
        attAct = activity as MainActivity
        headerTitle.text = getString(R.string.message)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context, presenter.conversations)
        }

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    override fun getLayoutId(): Int = R.layout.fragment_conversation

    override fun onStartLoadConversation() {
//        attAct.showProgress("正在获取会话列表")
    }

    override fun onLoadConversationSuccess() {
//        attAct.dismissProgress()
        showToast("获取会话列表成功")
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun onLoadConversationFailed() {
//        attAct.dismissProgress()
        showToast("获取会话列表失败")
    }

    override fun onResume() {
        super.onResume()
        presenter.loadConversation()
    }
    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}