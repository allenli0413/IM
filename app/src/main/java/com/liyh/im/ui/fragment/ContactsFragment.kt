package com.liyh.im.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hyphenate.chat.EMClient
import com.liyh.im.R
import com.liyh.im.adapter.ContactsListAdapter
import com.liyh.im.adapter.EmContactListenerAdapter
import com.liyh.im.contract.ContactsContract
import com.liyh.im.presenter.ContactsPresenter
import com.liyh.im.ui.widget.SlideBar
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 16 日
 * @time  10 时 04 分
 * @descrip :
 */
class ContactsFragment : BaseFragment(), ContactsContract.View {
    val presenter by lazy { ContactsPresenter(this) }

    override fun getLayoutId(): Int = R.layout.fragment_contacts

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
            setOnRefreshListener { presenter.loadContacts() }
        }

        recyclerView.apply {

            //            总结：当我们确定Item的改变不会影响RecyclerView的宽高的时候可以设置setHasFixedSize(true)，并通过Adapter的增删改插方法去刷新RecyclerView，而不是通过notifyDataSetChanged()。（其实可以直接设置为true，当需要改变宽高的时候就用notifyDataSetChanged()去整体刷新一下）
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactsListAdapter(context, presenter.contactsList)
        }
        presenter.loadContacts()

        EMClient.getInstance().contactManager().setContactListener(object : EmContactListenerAdapter() {
            override fun onContactDeleted(p0: String?) {
                super.onContactDeleted(p0)
                presenter.loadContacts()
            }
        })

        slideBar.onScetionChangeListener = object : SlideBar.OnScetionChangeListener {
            override fun onScetionChange(firstLetter: String) {
                section.visibility = View.VISIBLE
                section.text = firstLetter
            }

            override fun onSlideFinished() {
                section.visibility = View.GONE
            }

        }
    }

    override fun onLoadContactsSuccess() {
        swipeRefreshLayout.isRefreshing = false
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun onLoadContactsFailed() {
        swipeRefreshLayout.isRefreshing = false
        showToast(R.string.load_contacts_failed)
    }
}
