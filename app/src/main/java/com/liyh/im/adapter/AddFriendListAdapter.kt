package com.liyh.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.liyh.im.datas.AddFriendItemModel
import com.liyh.im.ui.widget.AddFriendListItemView

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 17 日
 * @time  16 时 28 分
 * @descrip :
 */
class AddFriendListAdapter(val context: Context, val addFriendList: MutableList<AddFriendItemModel>) : RecyclerView.Adapter<AddFriendListAdapter.AddFriendViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddFriendViewHolder = AddFriendViewHolder(AddFriendListItemView(context))

    override fun getItemCount(): Int = addFriendList.size

    override fun onBindViewHolder(holder: AddFriendViewHolder, position: Int) {
        val itemModel = addFriendList[position]
        val itemView = holder.itemView as AddFriendListItemView
        itemView.setData(itemModel)
    }


    class AddFriendViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}