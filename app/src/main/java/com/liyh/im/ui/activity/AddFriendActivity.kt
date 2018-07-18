package com.liyh.im.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.liyh.im.R
import com.liyh.im.adapter.AddFriendListAdapter
import com.liyh.im.contract.AddFriendContract
import com.liyh.im.presenter.AddFriendPresenter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 17 日
 * @time  16 时 14 分
 * @descrip :
 */
class AddFriendActivity : BaseActivity(), AddFriendContract.View{

    val presenter by lazy { AddFriendPresenter(this) }


    override fun onSearchSuccess() {
        dismissProgress()
        toast(R.string.search_success)
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun onSearchFailed() {
        dismissProgress()
        toast(R.string.search_failed)
    }

    override fun getLayoutId(): Int = R.layout.activity_add_friend

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.add_friend)
        search.setOnClickListener{
            goSearch()
        }

        userName.setOnEditorActionListener { v, actionId, event ->
            goSearch()
            true
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context, presenter.addFriendList)
        }
    }

    private fun goSearch() {
        hideSoftKeyBoard()
        showProgress(getString(R.string.searching))
        val userNameStr = userName.text.trim().toString()
        presenter.startSearch(userNameStr)
    }
}