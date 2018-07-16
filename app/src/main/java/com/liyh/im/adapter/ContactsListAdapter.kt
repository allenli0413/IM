package com.liyh.im.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.hyphenate.chat.EMClient
import com.liyh.im.R
import com.liyh.im.datas.ContactsItemModel
import com.liyh.im.ui.activity.ChatActivity
import com.liyh.im.widget.ContactsListItemView
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 16 日
 * @time  14 时 22 分
 * @descrip :
 */
class ContactsListAdapter(val context: Context, val list: List<ContactsItemModel>) : RecyclerView.Adapter<ContactsListAdapter.ContactsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsItemViewHolder = ContactsItemViewHolder(ContactsListItemView(context))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ContactsItemViewHolder, position: Int) {
        val itemView = holder.itemView as ContactsListItemView
        val itemModel = list[position]
        val userName = itemModel.contactName
        val dialogMsg = String.format(getStrRes(R.string.delete_friend_message), userName)
        itemView.setData(itemModel)
        itemView.setOnClickListener { context.startActivity<ChatActivity>("userName" to userName) }
        itemView.setOnLongClickListener {
            AlertDialog.Builder(context)
                    .setTitle(getStrRes(R.string.delete_friend_title))
                    .setMessage(dialogMsg)
                    .setNegativeButton(getStrRes(R.string.cancel), { dialog, which ->
                        dialog.dismiss()
                    })
                    .setPositiveButton(getStrRes(R.string.confirm), object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            deleteFriend(userName)
                        }

                    }).show()
            true
        }
    }

    private fun deleteFriend(userName: String) {
        EMClient.getInstance().contactManager().aysncDeleteContact(userName, object : EmCallbackAdapter() {
            override fun onSuccess() {
                super.onSuccess()
                context.runOnUiThread {
                    context.toast(getStrRes(R.string.delete_friend_success))
                }
            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                context.runOnUiThread {
                    context.toast(getStrRes(R.string.delete_friend_failed))
                }
            }
        })
    }

    class ContactsItemViewHolder(itemView: View) : ViewHolder(itemView)

    fun getStrRes(strRes: Int): String {
        return context.getString(strRes)
    }
}