package com.liyh.im.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE
import android.text.Editable
import android.view.View
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.liyh.im.R
import com.liyh.im.adapter.ChatListAdapter
import com.liyh.im.adapter.MessageListenerAdapter
import com.liyh.im.adapter.TextWatcherListenerAdapter
import com.liyh.im.contract.ChatContract
import com.liyh.im.presenter.ChatPresenter
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 16 日
 * @time  17 时 46 分
 * @descrip :
 */
class ChatActivity : BaseActivity(), ChatContract.View {
    override fun onLoadMessage() {
        recyclerView.adapter.notifyDataSetChanged()
        scrollToBottom()
    }

    override fun onStartSendMsg() {
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun onSendMsgSuccess() {
        recyclerView.adapter.notifyDataSetChanged()
        edit.text.clear()
        toast(R.string.send_message_success)
        scrollToBottom()
    }

    /**
     * 滚动到底部
     */
    private fun scrollToBottom() {
        recyclerView.scrollToPosition(presenter.msglist.size - 1)
    }

    override fun onSendMsgFailed() {
        recyclerView.adapter.notifyDataSetChanged()
        toast(R.string.send_message_failed)
    }

    override fun onLoadMoreMsg(size: Int) {
        recyclerView.adapter.notifyDataSetChanged()
        recyclerView.scrollToPosition(size)
    }

    override fun onLoadEmptyMsg() {
        toast(getString(R.string.text_empty_message))
    }
    val presenter by lazy { ChatPresenter(this) }
    lateinit var userName: String
    val messageListener = object : MessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            presenter.receiveMsg(userName, p0)
            runOnUiThread {
                recyclerView.adapter.notifyDataSetChanged()
                scrollToBottom()
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_chat

    override fun init() {
        super.init()
        initHeader()
        initEdit()
        initRecyclerView()
        send.setOnClickListener { sendMsg() }
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        presenter.loadMessage(userName)
    }

    private fun initRecyclerView() {
        recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ChatListAdapter(context, presenter.msglist)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == SCROLL_STATE_IDLE) {
                        val llm: LinearLayoutManager = layoutManager as LinearLayoutManager
                        val firstVisibleItemPosition = llm.findFirstVisibleItemPosition()
                        if (firstVisibleItemPosition == 0) {
                            presenter.loadMoreMessage(userName)
                        }
                    }
                }
            })
        }
    }

    private fun initEdit() {
        edit.addTextChangedListener(object : TextWatcherListenerAdapter() {
            override fun afterTextChanged(s: Editable?) {
                send.isEnabled = !s.isNullOrEmpty()
            }
        })
        edit.setOnEditorActionListener { v, actionId, event -> sendMsg() }
    }

    /**
     * 发送消息
     */
    private fun sendMsg(): Boolean {
        hideSoftKeyBoard()
        val message = edit.text.trim().toString()
        presenter.sendMsg(userName, message)
        return true
    }

    private fun initHeader() {
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }
        userName = intent.getStringExtra("userName")
        val title = String.format(getString(R.string.chat_title), userName)
        headerTitle.text = title
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}